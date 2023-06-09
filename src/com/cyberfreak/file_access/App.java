package com.cyberfreak.file_access;
public class App {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Server(8000, "students.txt"));
        t.start();
        t.join();
    }
}
