package com.example.studyonline_client.model;

public class WorkList {

    private int workId;
    private String workTopic;
    private String publishTime;
    private String commitTime;
    private float score;
    private int status;


    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getWorkTopic() {
        return workTopic;
    }

    public void setWorkTopic(String workTopic) {
        this.workTopic = workTopic;
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
