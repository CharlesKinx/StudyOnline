package com.example.studyonline_server.controller;


import com.example.studyonline_server.model.CommentInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")


public class CommentController {


    @PostMapping("/publish")
    public boolean publishComment(@RequestBody CommentInfo commentInfo){

        return true;
    }

}
