package com.example.studyonline_server.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class WorkInfoDTO {

    private int id;
    private String topic;
    private String teacherName;
    private String time;
    private int commitNumber;
    private int unCommitNumber;

}
