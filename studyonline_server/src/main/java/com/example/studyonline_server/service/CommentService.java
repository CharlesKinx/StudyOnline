package com.example.studyonline_server.service;

import com.example.studyonline_server.model.CommentInfo;
import com.example.studyonline_server.model.ResultInfo;

public interface CommentService {

    ResultInfo publishComment(CommentInfo commentInfo);
}
