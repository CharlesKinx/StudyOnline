package com.example.studyonline_server.mapper;


import com.example.studyonline_server.dto.CourseScoreDTO;
import com.example.studyonline_server.model.*;
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

    @Insert("insert into course_score (studentId,courseId,status,score) values (#{studentId},#{courseId},#{status},#{score})")
    void evaluateCourse(EvaluateCourseStarInfo evaluateCourseStarInfo);


    @Select("select * from choose_course,course where choose_course.courseId = course.id and choose_course.studentId = #{id}")
    @Results({
            @Result(property="viewNumber",column="view_number"),
            @Result(property="courseUrl",column="course_url")
    })
    ArrayList<CourseInfo> findMyCourse(int id);


    @Select("select name,score from choose_course,course where courseId=id and studentId = #{id}")
    @Results({
            @Result(property="courseName",column="name")
    })
    ArrayList<ScoreInfo> findCourseScore(int id);

    @Select("select name,avg(score) as avg_score from course,choose_course where id = courseId group by courseId ")
    @Results({
            @Result(property="courseName",column="name"),
            @Result(property="score",column="avg_score")
    })
    ArrayList<ScoreInfo> findAvgCourseScore();

    @Select("select name,max(score) as avg_score from course,choose_course where id = courseId group by courseId ")
    @Results({
            @Result(property="courseName",column="name"),
            @Result(property="score",column="avg_score")
    })
    ArrayList<ScoreInfo> findMaxCourseScore();
}
