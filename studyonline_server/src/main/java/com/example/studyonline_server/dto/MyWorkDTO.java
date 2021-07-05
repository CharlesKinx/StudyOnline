package com.example.studyonline_server.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyWorkDTO {

    private int workId;
    private String workTopic;
    private String publishTime;
    private String commitTime;
    private float score;
    private int status;
}
