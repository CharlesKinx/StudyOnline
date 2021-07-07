package com.example.studyonline_server.mapper;

import com.example.studyonline_server.dto.MyClassDTO;
import org.apache.ibatis.annotations.*;

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


    @Select("select classId from enter_class where studentId = #{studentId}")
    ArrayList<Integer> findMyClassList(int studentId);

    @Select("select id from class")
    ArrayList<Integer> findClassList();

    @Insert("insert into enter_class (classId,studentId) values (#{classId},#{studentId})")
    void enterClass(int studentId,int classId);


    @Select("select teacher.name,class_name,class.time,class.student_number,class.teacherId from enter_class,class,teacher where enter_class.classId = #{classId} and class.id = #{classId} and teacher.id = class.teacherId")
    @Results({
            @Result(property="teacherName",column = "name"),
            @Result(property="className",column="class_name"),
            @Result(property="time",column="time"),
            @Result(property="classNum",column="student_number"),
            @Result(property="teacherID",column="teacherId"),
    })
    MyClassDTO findClass(int classId);

    @Select("select count(studentId) from enter_class where studentId = #{studentId}")
    int findStudentClassNumber(int studentId);
}
