package com.example.studyonline_server.mapper;


import com.example.studyonline_server.dto.StudentInfoDTO;
import com.example.studyonline_server.model.AdministratorInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface AdministratorMapper {

    @Select("select * from admin where account = #{account}")
    AdministratorInfo findByAccount(String account);

    @Insert("insert into admin (account,password,telephone,name) values (#{account},#{password},#{telephone},#{name})")
    void insertAdmin(AdministratorInfo administratorInfo);



}
