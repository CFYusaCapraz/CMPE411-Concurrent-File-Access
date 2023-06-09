package com.cyberfreak.file_access;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private int PORT;
    private String fileName;

    public Server(int port, String fileName) {
        this.PORT = port;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        if (StudentManager.loadStudentsFile(this.fileName)) {
            try (ServerSocket socket = new ServerSocket(PORT)) {
                while (true) {
                    Socket childSocket = socket.accept();
                    new Thread(new ClientHandler(childSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
