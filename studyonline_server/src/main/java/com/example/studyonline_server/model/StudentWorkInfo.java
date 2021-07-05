package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class StudentWorkInfo {

    private int studentId;
    private int workId;
    private int status;
    private int fileId;
    private float score;
    private String content;
    private String commitTime;

}
