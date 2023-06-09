package com.cyberfreak.file_access;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server implements Runnable{

    private int PORT;
    private String fileName;
    private LinkedList<Student> studentList;

    public Server(int port, String fileName) {
        this.PORT = port;
        this.fileName = fileName;
    }

    private boolean loadStudentsFile(){
        File f = new File(this.fileName);
        try (FileReader reader = new FileReader(f); BufferedReader bReader = new BufferedReader(reader)) {
            String line;
            while ((line = bReader.readLine())!= null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String cgpa = fields[2];
                String birthday = fields[3];
                String gender = fields[4];
                studentList.add(new Student(id, name, cgpa, birthday, gender));
            }
        } catch (IOException e) {
            System.out.println("Students file could not be found!");
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        if(loadStudentsFile()){
            try {
                ServerSocket socket = new ServerSocket(PORT);
                while (true) {
                    Socket childSocket = socket.accept();
                    // Will imeplement the multithreaded childs here
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
