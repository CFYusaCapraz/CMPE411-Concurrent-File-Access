package com.cyberfreak.file_access.server;

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

    public static void main(String[] args) throws InterruptedException {
        Server s;
        int p = Integer.valueOf(args[0]);
        String f = args[1];
        if (p != 0 && f != null) {
            s = new Server(p, f);

        } else {
            s = new Server(8000, "students.txt");
        }

        Thread t = new Thread(s);
        t.start();
        String msg = String.format("[*] Server started listening on port %d", s.PORT);
        System.out.println(msg);
        t.join();
        System.out.println("Server closing...");

    }

}
