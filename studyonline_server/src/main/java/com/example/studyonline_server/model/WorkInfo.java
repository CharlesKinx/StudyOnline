package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class WorkInfo {

    private int id;
    private int workId;
    private int studentId;
    private String commitTime;
    private String url;
    private String fileName;
    private String fileType;
    private String content;
    private int status;

}
