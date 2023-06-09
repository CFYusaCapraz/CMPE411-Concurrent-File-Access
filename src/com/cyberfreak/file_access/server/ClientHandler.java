package com.cyberfreak.file_access.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket incomingClient) {
        client = incomingClient;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()))) {
            InetAddress addr = client.getInetAddress();
            int port = client.getPort();
            String ipString = "";
            byte[] addressBytes = addr.getAddress();
            for (int i = 0; i < addressBytes.length; i++) {
                ipString += (addressBytes[i] & 0xFF);
                if (i < addressBytes.length - 1) {
                    ipString += ".";
                }
            }
            ipString += ":" + port;
            String msg = String.format("[+] Connection established from: %s", ipString);
            System.out.println(msg);
            while (true) {
                boolean flag = false;
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    msg = String.format("[!] Command `%s` from: %s", line, ipString);
                    System.out.println(msg);

                    String[] fields = line.split(" ");
                    switch (fields[0]) {
                        case "display":
                            if (fields.length > 1)
                                StudentManager.displayOneStudent(out, fields[1]);
                            else
                                StudentManager.displayStudents(out);
                            break;
                        case "delete":
                            StudentManager.deleteStudent(fields[1]);
                            break;
                        case "modify":
                            StudentManager.modifyCGPA(fields[1], fields[2]);
                            break;
                        case "add":
                            int offset = fields.length - 6;
                            String name = "";
                            for (int i = 0; i <= offset; i++) {
                                name += fields[2 + i] + " ";
                            }
                            name = name.trim();
                            StudentManager
                                    .addStudent(new Student(fields[1], name, fields[3 + offset], fields[4 + offset],
                                            fields[5 + offset]));
                            break;
                        case "disconnect":
                            flag = true;
                            break;
                    }
                }
                if (flag)
                    break;
            }
            msg = String.format("[-] Connection closed from: %s", ipString);
            System.out.println(msg);
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
