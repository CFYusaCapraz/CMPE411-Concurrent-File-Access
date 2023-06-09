package com.cyberfreak.file_access;

public class Student {
    private String studentID;
    private String studentName;
    private String studentCGPA;
    private String studentBirthday;
    private char studentGender;

    public Student() {
    }

    public Student(String studentID, String studentName, String studentCGPA, String studentBirthday,
            char studentGender) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentCGPA = studentCGPA;
        this.studentBirthday = studentBirthday;
        this.studentGender = studentGender;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCGPA() {
        return this.studentCGPA;
    }

    public void setStudentCGPA(String studentCGPA) {
        this.studentCGPA = studentCGPA;
    }

    public String getStudentBirthday() {
        return this.studentBirthday;
    }

    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    public char getStudentGender() {
        return this.studentGender;
    }

    public void setStudentGender(char studentGender) {
        this.studentGender = studentGender;
    }

}
