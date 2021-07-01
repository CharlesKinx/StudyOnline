package com.example.studyonline_server.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommentDTO {

    private int courseId;
    private String name;
    private String time;
    private String content;
    private float score;

}
