package com.example.studyonline_server.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommentInfoDTO {

    private int id;
    private String studentName;
    private String content;
    private String time;
    private String courseName;
}
