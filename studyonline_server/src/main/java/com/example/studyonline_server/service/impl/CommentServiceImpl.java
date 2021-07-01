package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.mapper.CommentMapper;
import com.example.studyonline_server.model.CommentInfo;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResultInfo publishComment(CommentInfo commentInfo) {
        commentMapper.publishComment(commentInfo);
        System.out.println(commentInfo.getId());
        return null;
    }
}
