package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.mapper.CourseListMapper;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.TeacherInfo;
import com.example.studyonline_server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseListMapper courseListMapper;

    @Override
    public ArrayList<CourseListDTO> getCourseList() {

        ArrayList<CourseListDTO> courseListDTOArrayList = new ArrayList<>();
        ArrayList<CourseInfo> courseInfos = getCourseListInfo();
        ArrayList<TeacherInfo> teacherInfos = getTeacherListInfo();

        for(CourseInfo courseInfo : courseInfos){
            CourseListDTO courseListDTO = new CourseListDTO();
            courseListDTO.setId(courseInfo.getId());
            courseListDTO.setName(courseInfo.getName());
            courseListDTO.setTime(courseInfo.getTime());
            courseListDTO.setTeacherName(findTeacherName(courseInfo.getId(),teacherInfos));
            courseListDTOArrayList.add(courseListDTO);
        }
        return courseListDTOArrayList;
    }


    private ArrayList<CourseInfo> getCourseListInfo(){
        ArrayList<CourseInfo> courseInfos = courseListMapper.findAllCourse();
        return courseInfos;
    }


    private ArrayList<TeacherInfo> getTeacherListInfo(){
        ArrayList<TeacherInfo> teacherInfos = courseListMapper.findAllTeacher();
        return teacherInfos;
    }

    private String findTeacherName(int id,ArrayList<TeacherInfo> teacherInfos){
        for (TeacherInfo teacherInfo : teacherInfos){
            if (id == teacherInfo.getId()){
                return teacherInfo.getName();
            }
        }

        return null;
    }
}
