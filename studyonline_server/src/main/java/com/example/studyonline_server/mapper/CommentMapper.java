package com.example.studyonline_server.mapper;

import com.example.studyonline_server.model.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    ArrayList<CommentInfo> findAllComments();

}
