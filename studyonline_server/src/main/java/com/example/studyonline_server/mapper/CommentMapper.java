package com.example.studyonline_server.mapper;

import com.example.studyonline_server.model.CommentInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    ArrayList<CommentInfo> findAllComments();

    @Insert("insert into comment (studentId,courseId,content,time) values (#{studentId},#{courseId},#{content},#{time})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void publishComment(CommentInfo commentInfo);


}
