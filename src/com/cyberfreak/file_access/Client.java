package com.cyberfreak.file_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.menu();
    }

    private void menu() {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("WELCOME");
        System.out.println("Before using the service please enter the ip address and the port number of the server");
        System.out.print("IP Address: ");
        String ip = consoleIn.nextLine().trim();
        System.out.print("Port Number: ");
        int port = Integer.parseInt(consoleIn.nextLine().trim());
        System.out.printf("Connecting to `%s:%d`%s", ip, port, System.getProperty("line.separator"));

        try (Socket socket = new Socket(ip, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            boolean x;
            do {
                x = cmd(consoleIn, in, out);
            } while (x);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not connect to the server!");
        }
        consoleIn.close();
    }

    private boolean cmd(Scanner console, BufferedReader in, PrintWriter out) throws IOException {
        System.out.print("cmd-> ");
        String cmd = console.nextLine().trim();
        short opt = getOption(cmd);
        switch (opt) {
            case 0:
                return true;
            case 1:
                display(in, out);
                return true;
            case 2:
                delete(console, out);
                return true;
            case 3:
                modify(console, out);
                return true;
            case 4:
                add(console, out);
                return true;
            case 5:
                System.out.println("Disconnecting from the server");
                return false;
            case -1:
                return true;
        }
        return false;
    }

    private short getOption(String line) {
        short result;
        String cmd = line.split(" ")[0].toLowerCase();
        if (cmd == null)
            result = 0;
        else {
            switch (cmd) {
                case "display":
                    result = 1;
                    break;
                case "delete":
                    result = 2;
                    break;
                case "modify":
                    result = 3;
                    break;
                case "add":
                    result = 4;
                    break;
                case "disconnect":
                    result = 5;
                    break;
                default:
                    result = -1;
                    break;
            }
        }
        return result;
    }

    private void display(BufferedReader in, PrintWriter out) throws IOException {
        out.println("display");
        out.flush();
        String line;
        while ((line = in.readLine()) != null) {
            if (line.equals("."))
                break;
            System.out.println(line);
        }
    }

    private void delete(Scanner console, PrintWriter out) throws IOException {
        System.out.print("Please enter the student ID: ");
        String id = console.nextLine().trim();
        if (id != null && !id.isBlank()) {
            out.printf("delete %s%s", id, System.getProperty("line.separator"));
            out.flush();
        } else {
            System.out.println("Please enter a valid input!");
        }
    }

    private void modify(Scanner console, PrintWriter out) throws IOException {
        System.out.print("Please enter the student ID: ");
        String id = console.nextLine().trim();
        if (id != null && !id.isBlank()) {
            System.out.print("Please enter the new CGPA (X.XX): ");
            String cgpa = console.nextLine().trim();
            if (cgpa != null && !cgpa.isBlank()) {
                out.printf("modify %s %s%s", id, cgpa, System.getProperty("line.separator"));
                out.flush();
            } else
                System.out.println("Please enter a valid input!");

        } else {
            System.out.println("Please enter a valid input!");
        }
    }

    private void add(Scanner console, PrintWriter out) throws IOException {
        System.out.print("Please enter the student's ID (XXXX): ");
        String id = console.nextLine().trim();
        if (id != null && !id.isBlank()) {
            System.out.print("Please enter the student's name (30 characters max): ");
            String name = console.nextLine().trim();
            if (name != null && !name.isBlank()) {
                System.out.print("Please enter the student's CGPA (X.XX): ");
                String cgpa = console.nextLine().trim();
                if (cgpa != null && !cgpa.isBlank()) {
                    System.out.print("Please enter the student's birthday (XX-XX-XXXX): ");
                    String birthday = console.nextLine().trim();
                    if (birthday != null && !birthday.isBlank()) {
                        System.out.print("Please enter the student's gender (M/F): ");
                        String gender = console.nextLine().toUpperCase().trim();
                        if (gender != null && !gender.isBlank()) {
                            out.printf("add %s %s %s %s %s%s", id, name, cgpa, birthday, gender,
                                    System.getProperty("line.separator"));
                            out.flush();
                        } else {
                            System.out.println("Please enter a valid input!");
                        }
                    } else {
                        System.out.println("Please enter a valid input!");
                    }
                } else {
                    System.out.println("Please enter a valid input!");
                }
            } else {
                System.out.println("Please enter a valid input!");
            }
        } else {
            System.out.println("Please enter a valid input!");
        }
    }
}
