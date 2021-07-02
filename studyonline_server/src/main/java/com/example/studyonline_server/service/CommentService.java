package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.CommentDTO;
import com.example.studyonline_server.model.CommentInfo;
import com.example.studyonline_server.model.ResultInfo;

import java.util.ArrayList;

public interface CommentService {

    CommentDTO publishComment(CommentInfo commentInfo);
    ArrayList<CommentDTO> getCommentList(String string);
}
