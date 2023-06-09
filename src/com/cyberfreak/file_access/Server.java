package com.cyberfreak.file_access;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private int PORT;

    public Server(int port) {
        this.PORT = port;
    }

    @Override
    public void run() {
    }
    
}
