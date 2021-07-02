package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class CourseScoreInfo {
    private int courseId;
    private int studentId;
    private float score;
}
