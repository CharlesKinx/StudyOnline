package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CourseInfo {

    private String name;
    private String introduce;
    private String evaluation;
    private String time;
    private int id;
    private int teacherId;
    private int viewNumber;
    private String courseUrl;

}
