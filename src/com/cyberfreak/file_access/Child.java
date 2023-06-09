package com.cyberfreak.file_access;

import java.net.Socket;

public class Child implements Runnable{
    private Socket client;
    public Child(Socket incomingClient){
        client = incomingClient;
    }
	@Override
	public void run() {
	}
}
