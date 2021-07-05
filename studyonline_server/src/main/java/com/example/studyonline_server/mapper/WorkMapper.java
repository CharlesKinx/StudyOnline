package com.example.studyonline_server.mapper;

import com.example.studyonline_server.dto.MyWorkDTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WorkMapper {

    @Select("select work.topic,work.time,student_work.commit_time,student_work.score,student_work.status from work,student_work where work.teacherId = #{teacherId} and student_work.studentId = #{studentId} and student_work.workId = work.id ")
    @Results({
            @Result(property="workTopic",column = "topic"),
            @Result(property="publishTime",column="time"),
            @Result(property="commitTime",column="commit_time"),
    })
    ArrayList<MyWorkDTO> findClassWork(@Param("studentId") int studentId, @Param("teacherId") int teacherId);




}
