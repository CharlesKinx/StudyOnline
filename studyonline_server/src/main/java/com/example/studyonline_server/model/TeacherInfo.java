package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class TeacherInfo {
    private int id;
    private String account;
    private String password;
    private String sex;
    private String telephone;
    private String name;
    private int age;
    private int role;

}
