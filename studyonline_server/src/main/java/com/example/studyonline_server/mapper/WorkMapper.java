package com.example.studyonline_server.mapper;

import com.example.studyonline_server.dto.MyWorkDTO;
import com.example.studyonline_server.model.StudentWorkInfo;
import com.example.studyonline_server.model.WorkFileInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WorkMapper {

    @Select("select work.topic,work.time,student_work.commit_time,student_work.score,work_id,student_work.status from work,student_work where work.teacherId = #{teacherId} and student_work.studentId = #{studentId} and student_work.workId = work_id ")
    @Results({
            @Result(property="workTopic",column = "topic"),
            @Result(property="publishTime",column="time"),
            @Result(property="commitTime",column="commit_time"),
            @Result(property="workId",column="work_id")
    })
    ArrayList<MyWorkDTO> findClassWork(@Param("studentId") int studentId, @Param("teacherId") int teacherId);



    @Update("update student_work set status = #{status},fileId = #{fileId},content=#{content},commit_time = #{commitTime} where studentId = #{studentId} and workId = #{workId} ")
    void updateWork(StudentWorkInfo studentWorkInfo);


    @Insert("insert into file (name,type,url) values (#{name},#{type},#{url}) ")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertFile(WorkFileInfo workFileInfo);


    @Select("select count(studentId) from student_work where studentId = #{studentId} and status = 1")
    int findStudentWorkNumber(int studentId);
}
