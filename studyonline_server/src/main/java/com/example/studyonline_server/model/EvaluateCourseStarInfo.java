package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EvaluateCourseStarInfo {
    private int studentId;
    private int courseId;
    private float score;
    private int status;
}
