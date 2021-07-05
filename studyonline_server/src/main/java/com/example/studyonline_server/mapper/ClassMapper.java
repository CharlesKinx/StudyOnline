package com.example.studyonline_server.mapper;

import com.example.studyonline_server.dto.MyClassDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ClassMapper {

    @Select("select teacher.name,class_name,class.time,class.student_number,class.teacherId from enter_class,class,teacher where enter_class.studentId = #{id} and enter_class.classId = class.id and teacher.id = class.teacherId ")

    @Results({
            @Result(property="teacherName",column = "name"),
            @Result(property="className",column="class_name"),
            @Result(property="time",column="time"),
            @Result(property="classNum",column="student_number"),
            @Result(property="teacherID",column="teacherId"),


    })
    ArrayList<MyClassDTO> findMyClass(int id);

}
