package com.example.studyonline_server.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class TeacherInfoDTO {
    private int id;
    private String name;
    private String account;
    private String password;
    private int age;
    private String sex;
    private String courseName;
    private String className;
    private int publishWorkNum;
    private String telephone;

}
