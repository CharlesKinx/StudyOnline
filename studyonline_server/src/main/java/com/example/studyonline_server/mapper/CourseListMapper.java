package com.example.studyonline_server.mapper;


import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CourseListMapper {

    @Select("select * from course")
    ArrayList<CourseInfo> findAllCourse();

    @Select("select * from teacher")
    ArrayList<TeacherInfo> findAllTeacher();

    @Select("select * from course where id = #{id}")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="viewNumber",column="view_number")
    })
    CourseInfo findById(int id);
}
