package com.example.studyonline_server.controller;


import com.example.studyonline_server.dto.CommentDTO;
import com.example.studyonline_server.model.CommentInfo;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/publish")
    public CommentDTO publishComment(@RequestBody CommentInfo commentInfo){
        return commentService.publishComment(commentInfo);
    }

    @PostMapping("/list")
    public ArrayList<CommentDTO> getCommentList(@RequestBody String string){
        return commentService.getCommentList(string);
    }

}
