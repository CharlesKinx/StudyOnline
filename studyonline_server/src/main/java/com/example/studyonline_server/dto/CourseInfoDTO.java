package com.example.studyonline_server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CourseInfoDTO {

    private int id;
    private String name;
    private String teacherName;
    private String introduce;
    private String time;
    private int viewNumber;
    private String evaluation;
    private int courseNumber;
}
