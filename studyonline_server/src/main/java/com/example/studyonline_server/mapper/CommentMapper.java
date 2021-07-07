package com.example.studyonline_server.mapper;

import com.example.studyonline_server.dto.CommentDTO;
import com.example.studyonline_server.dto.CommentInfoDTO;
import com.example.studyonline_server.model.CommentInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    ArrayList<CommentInfo> findAllComments();

    @Insert("insert into comment (studentId,courseId,content,time) values (#{studentId},#{courseId},#{content},#{time})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void publishComment(CommentInfo commentInfo);



    @Select("select comment.courseId,student.name,comment.time,comment.content,course_score.score from course,student,course_score,comment where course.id = #{ID} and course.id = comment.courseId and student.id = comment.studentId and course.id = course_score.courseId and student.id = course_score.studentId")
    ArrayList<CommentDTO> findComment(int ID);


    @Select("select count(studentId) from comment where studentId = #{studentId}")
    int findStudentCommentNumber(int studentId);


    @Select("select comment.id,student.name,content,comment.time,course.name from comment,student,course where studentId = student.id and courseId = course.id ")
    @Results({
            @Result(property="courseName",column="name"),
            @Result(property="studentName",column="avg_score")
    })
    ArrayList<CommentInfoDTO> findAllCommentInfo();
}
