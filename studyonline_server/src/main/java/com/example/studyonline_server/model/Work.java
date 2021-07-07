package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Work {
    private int work_id;
    private String topic;
    private int teacherId;
    private String time;

}
