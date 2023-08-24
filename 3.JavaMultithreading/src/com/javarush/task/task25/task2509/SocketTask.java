package com.javarush.task.task25.task2509;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketTask<T> implements CancellableTask<T> {
    private Socket socket;

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public synchronized void cancel() {
        //close all resources here
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketTask.this.cancel();
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                } finally {
                    super.cancel(mayInterruptIfRunning);
                }
                return false;
            }
        };
    }
}