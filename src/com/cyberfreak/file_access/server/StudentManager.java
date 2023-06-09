package com.cyberfreak.file_access.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class StudentManager {
    private static LinkedList<Student> studentList = new LinkedList<Student>();
    private static String fileName;

    public static boolean loadStudentsFile(String studentsFileName) {
        fileName = studentsFileName;
        File f = new File(studentsFileName);
        try (FileReader reader = new FileReader(f); BufferedReader bReader = new BufferedReader(reader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String cgpa = fields[2];
                String birthday = fields[3];
                String gender = fields[4];
                studentList.add(new Student(id, name, cgpa, birthday, gender));
            }
        } catch (IOException e) {
            System.out.println("An error occured!");
            return false;
        }
        return true;
    }

    private synchronized static void saveChanges() {
        File f = new File(fileName);
        try (FileWriter writer = new FileWriter(f); PrintWriter pWriter = new PrintWriter(writer)) {
            ListIterator<Student> it = studentList.listIterator();
            while (it.hasNext()) {
                Student tmp = it.next();
                String data = String.format("%4s,%-30s,%4s,%10s,%1s", tmp.getStudentID(), tmp.getStudentName(),
                        tmp.getStudentCGPA(),
                        tmp.getStudentBirthday(), tmp.getStudentGender());
                pWriter.println(data);
            }
        } catch (IOException e) {
            System.out.println("An error occured!");
        }
    }

    public synchronized static Student searchStudent(String id) {
        Student found = null;
        ListIterator<Student> it = studentList.listIterator();
        while (it.hasNext()) {
            Student tmp = it.next();
            if (tmp.getStudentID().equals(id)) {
                found = tmp;
                break;
            }
        }
        return found;
    }

    public synchronized static boolean deleteStudent(String id) {
        Student tmp = searchStudent(id);
        if (tmp != null) {
            if (tmp.getStudentID().equals(id)) {
                studentList.remove(tmp);
                saveChanges();
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean addStudent(Student newStudent) {
        Student tmp = searchStudent(newStudent.getStudentID());
        if (tmp == null) {
            studentList.add(newStudent);
            saveChanges();
            return true;
        } else {
            return false;
        }
    }

    public synchronized static boolean modifyCGPA(String id, String cgpa) {
        Student tmp = searchStudent(id);
        if (tmp != null) {
            tmp.setStudentCGPA(cgpa);
            saveChanges();
            return true;
        } else {
            return false;
        }
    }

    public synchronized static void displayStudents(PrintWriter out) {
        String sep = "-".repeat(68);
        out.println(sep);
        String header = String.format("%-4s | %-30s | %4s | %-10s | %s |", "ID", "Name Surname", "CGPA",
                "Birthday", "Gender");
        out.println(header);
        out.println(sep);
        out.flush();
        ListIterator<Student> it = studentList.listIterator();
        while (it.hasNext()) {
            Student tmp = it.next();
            String data = String.format("%4s | %-30s | %4s | %10s | %-6s |", tmp.getStudentID(), tmp.getStudentName(),
                    tmp.getStudentCGPA(),
                    tmp.getStudentBirthday(), tmp.getStudentGender());
            out.println(data);
            out.println(sep);
            out.flush();
        }
        out.println(".");
        out.flush();
    }

    public synchronized static void displayOneStudent(PrintWriter out, String id) {
        Student tmp = searchStudent(id);
        if (tmp != null) {
            String sep = "-".repeat(68);
            out.println(sep);
            String header = String.format("%-4s | %-30s | %4s | %-10s | %s |", "ID", "Name Surname", "CGPA",
                    "Birthday", "Gender");
            out.println(header);
            out.println(sep);

            String data = String.format("%4s | %-30s | %4s | %10s | %-6s |", tmp.getStudentID(), tmp.getStudentName(),
                    tmp.getStudentCGPA(),
                    tmp.getStudentBirthday(), tmp.getStudentGender());
            out.println(data);
            out.println(sep);
            out.println(".");
            out.flush();
        } else {
            out.println("Student not found!");
        }
    }
}
