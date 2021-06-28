package com.example.studyonline_server.mapper;


import com.example.studyonline_server.model.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {


    @Select("select * from student where id = #{id}")
    StudentInfo findById(Integer id);

    @Select("select * from student where account = #{account}")
    StudentInfo findByAccount(String account);


}
