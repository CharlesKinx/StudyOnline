package com.example.studyonline_server.dto;

import com.example.studyonline_server.model.CourseScoreInfo;
import com.example.studyonline_server.model.ScoreInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString

public class CourseScoreDTO {

    private ArrayList<ScoreInfo> myScore;
    private ArrayList<ScoreInfo> averageScore;
    private ArrayList<ScoreInfo> maxScore;

}
