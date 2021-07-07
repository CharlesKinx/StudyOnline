package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.CommentDTO;
import com.example.studyonline_server.mapper.CommentMapper;
import com.example.studyonline_server.mapper.CourseMapper;
import com.example.studyonline_server.mapper.StudentMapper;
import com.example.studyonline_server.model.CommentInfo;
import com.example.studyonline_server.model.EvaluateCourseStarInfo;
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

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CommentDTO publishComment(CommentInfo commentInfo) {


        EvaluateCourseStarInfo evaluateCourseStarInfo = courseMapper.findEvaluation(commentInfo.getCourseId(),commentInfo.getStudentId());
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName(studentMapper.findNameById(commentInfo.getStudentId()));
        commentDTO.setTime(commentInfo.getTime());
        commentDTO.setContent(commentInfo.getContent());
        commentDTO.setScore(evaluateCourseStarInfo.getScore());
        commentMapper.publishComment(commentInfo);
        return commentDTO;
    }


    @Override
    public ArrayList<CommentDTO> getCommentList(String string) {
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");
        return commentMapper.findComment(id);
    }
}
