package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    public static void main(String[] args) throws IOException {
        try(ServerSocket socket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер запущен!");
            while (true) {
                Socket connection = socket.accept();
                new Handler(connection).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (String user : connectionMap.keySet()) {
            try {
                connectionMap.get(user).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не было доставлено пользователю " + user);
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            String userName = null;
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом" + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)){
                userName = serverHandshake(connection);

                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));

                notifyUsers(connection, userName);

                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("При обмене данными с указанным адресом возникла ошикба:\n" + e.getMessage());
            }
            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Соединение с " + socket.getRemoteSocketAddress() + " закрыто");

        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message reply = connection.receive();

                if (reply.getType() != MessageType.USER_NAME)
                    continue;

                String name = reply.getData();
                if (name.isEmpty() || connectionMap.containsKey(name))
                    continue;

                connectionMap.put(name, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return name;
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String user : connectionMap.keySet()) {
                if (user.equals(userName))
                    continue;
                 connection.send(new Message(MessageType.USER_ADDED, user));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            String text;
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    text = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else {
                    ConsoleHelper.writeMessage("Сообщение не было доставлено. Ожидаемый тип сообщения: TEXT, полученный: " + message.getType());
                }
            }
        }
    }
}
