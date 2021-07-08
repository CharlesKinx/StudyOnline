package com.example.studyonline_server.mapper;


import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.model.TeacherInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StudentMapper {


    @Select("select * from student where id = #{id}")
    StudentInfo findById(Integer id);

    @Select("select id from student where account = #{account}")
    int findIdByAccount(String account);

    @Select("select * from student where account = #{account}")
    StudentInfo findByAccount(String account);

    @Insert("insert into student (account,password,telephone,name,role,age,sex) values(#{account},#{password},#{telephone},#{name},#{role},#{age},#{sex})")
    void registerStudent(StudentInfo studentInfo);

    @Update("update student set name = #{name}, telephone=#{telephone},age=#{age},sex=#{sex},password =#{password} ,account =#{account} where id = #{id}")
    void updateStudentInfo(StudentInfo studentInfo);

    @Select("select name from student where id = #{id}")
    String findNameById(int id);


    @Select("select * from student")
    ArrayList<StudentInfo> findAllStudent();

    @Delete("delete from student where account =#{account}")
    void deleteStudent(String account);

}
