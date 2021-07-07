package com.example.studyonline_server.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@ToString

public class StudentInfoDTO {

    private String name;
    private String account;
    private String password;
    private String telephone;
    private int courseNumber;
    private int classNumber;
    private String sex;
    private int age;
    private int commentNumber;
    private int workNumber;
    private int role;

}
