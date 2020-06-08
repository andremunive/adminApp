package com.example.adminapp.studentsPackage;

public class students {
    public String name;
    public String lastName;
    public String user;
    public String email;
    public String password;
    public String courseID;

    public students() {
    }

    public students(String name, String lastName, String user, String email, String password, String courseID) {
        this.name = name;
        this.lastName = lastName;
        this.user = user;
        this.email = email;
        this.password = password;
        this.courseID = courseID;
    }
}
