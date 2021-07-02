package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.CommentDTO;
import com.example.studyonline_server.mapper.CommentMapper;
import com.example.studyonline_server.model.CommentInfo;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.service.CommentService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResultInfo publishComment(CommentInfo commentInfo) {

        commentMapper.publishComment(commentInfo);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg("成功发布");
        resultInfo.setSuccess(true);
        resultInfo.setData(commentInfo);

        return resultInfo;
    }


    @Override
    public ArrayList<CommentDTO> getCommentList(String string) {
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");
        return commentMapper.findComment(id);
    }
}
