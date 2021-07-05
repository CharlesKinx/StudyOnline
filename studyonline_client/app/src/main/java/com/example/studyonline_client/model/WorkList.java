package com.example.studyonline_client.model;

public class WorkList {
    private String workTopic;
    private String time;
    private float score;
    private int status;

    public String getWorkTopic() {
        return workTopic;
    }

    public void setWorkTopic(String workTopic) {
        this.workTopic = workTopic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
