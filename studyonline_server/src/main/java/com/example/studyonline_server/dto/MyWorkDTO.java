package com.example.studyonline_server.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyWorkDTO {

    private String workTopic;
    private String time;
    private float score;
    private int status;

}
