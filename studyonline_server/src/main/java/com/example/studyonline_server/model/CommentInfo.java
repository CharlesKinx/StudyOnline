package com.example.studyonline_server.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class CommentInfo {
    private int id;
    private int studentId;
    private int courseId;
    private String content;
    private String time;
}
