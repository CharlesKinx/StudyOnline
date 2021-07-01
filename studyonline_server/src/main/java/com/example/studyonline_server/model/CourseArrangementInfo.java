package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CourseArrangementInfo {
    private int id;
    private int courseId;
    private String content;
}
