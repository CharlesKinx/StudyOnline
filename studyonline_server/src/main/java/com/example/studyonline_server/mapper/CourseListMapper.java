package com.example.studyonline_server.mapper;


import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.TeacherInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CourseListMapper {

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


}
