package com.example.studyonline_server.mapper;


import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.model.TeacherInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeacherMapper {

    @Insert("insert into teacher (account,password,telephone,name,role,age,sex) values(#{account},#{password},#{telephone},#{name},#{role},#{age},#{sex})")
    void registerTeacher(TeacherInfo teacherInfo);


    @Select("select * from teacher where id = #{id}")
    TeacherInfo findById(Integer id);

    @Select("select id from teacher where account = #{account}")
    int findIdByAccount(String account);

    @Select("select * from teacher where account = #{account}")
    TeacherInfo findByAccount(String account);


    @Update("update teacher set name = #{name}, telephone=#{telephone},age=#{age},sex=#{sex},password =#{password} ,account =#{account} where id = #{id}")
    void updateStudentInfo(TeacherInfo teacherInfo);

}
