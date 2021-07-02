package com.example.studyonline_server.mapper;


import com.example.studyonline_server.model.CourseArrangementInfo;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.EvaluateCourseStarInfo;
import com.example.studyonline_server.model.TeacherInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CourseMapper {

    @Select("select * from course")
    @Results({
            @Result(property="viewNumber",column="view_number"),
            @Result(property="courseUrl",column="course_url")
    })
    ArrayList<CourseInfo> findAllCourse();

    @Select("select * from teacher")
    ArrayList<TeacherInfo> findAllTeacher();

    @Select("select * from course where id = #{id}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="viewNumber",column="view_number"),
            @Result(property="courseUrl",column="course_url")
    })
    CourseInfo findById(int id);


    @Select("select course_url from course where id = #{id}")
    String findImgUrl(@Param("id") int id);


    @Select("select * from course_arrangement where courseId = #{courseId}")
    ArrayList<CourseArrangementInfo> findCourseArrangement(int courseId);


    @Select("select * from course_score where courseId = #{courseId} and studentId = #{studentId}")
    EvaluateCourseStarInfo findEvaluation(int courseId,int studentId);

}
