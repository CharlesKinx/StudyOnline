package com.example.studyonline_client.model;

import java.util.ArrayList;

public class CourseScoreAnalysisInfo {
    private ArrayList<ScoreInfo> myScore;
    private ArrayList<ScoreInfo> averageScore;
    private ArrayList<ScoreInfo> maxScore;

    public ArrayList<ScoreInfo> getMyScore() {
        return myScore;
    }

    public void setMyScore(ArrayList<ScoreInfo> myScore) {
        this.myScore = myScore;
    }

    public ArrayList<ScoreInfo> getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(ArrayList<ScoreInfo> averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<ScoreInfo> getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(ArrayList<ScoreInfo> maxScore) {
        this.maxScore = maxScore;
    }
}
