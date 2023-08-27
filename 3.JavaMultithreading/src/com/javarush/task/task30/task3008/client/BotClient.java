package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random()*100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            BotClient.this.sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] splittedMessage = message.split(": ");
            if (splittedMessage.length < 2)
                return;
            String userName = splittedMessage[0];
            String text = splittedMessage[1];
            String pattern = null;
            switch (text) {
                case "дата":
                    pattern = "d.MM.YYYY";
                    break;
                case "день":
                    pattern = "d";
                    break;
                case "месяц":
                    pattern = "MMMM";
                    break;
                case "год":
                    pattern = "YYYY";
                    break;
                case "время":
                    pattern = "H:mm:ss";
                    break;
                case "час":
                    pattern = "H";
                    break;
                case "минуты":
                    pattern = "m";
                    break;
                case "секунды":
                    pattern = "s";
                    break;
            }
            if (pattern == null) return;
            String data = new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
            BotClient.this.sendTextMessage(String.format("Информация для %s: %s", userName, data));
        }
    }
}
