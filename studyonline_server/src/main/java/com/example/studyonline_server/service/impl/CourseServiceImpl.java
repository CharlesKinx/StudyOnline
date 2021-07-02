package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.mapper.CourseMapper;
import com.example.studyonline_server.model.CourseArrangementInfo;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.EvaluateCourseStarInfo;
import com.example.studyonline_server.model.TeacherInfo;
import com.example.studyonline_server.service.CourseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

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
            courseListDTO.setImgUrl(courseInfo.getCourseUrl());
            courseListDTO.setTeacherName(findTeacherName(courseInfo.getId(),teacherInfos));
            courseListDTOArrayList.add(courseListDTO);
        }
        return courseListDTOArrayList;
    }

    @Override
    public CourseInfo getCourseInfo(int id) {
        CourseInfo courseInfo = courseMapper.findById(id);
        return courseInfo;
    }

    @Override
    public String getCourseImg(int id) {

        return courseMapper.findImgUrl(id);

    }

    @Override
    public ArrayList<CourseArrangementInfo> getCourseArrangement(int id) {
        return courseMapper.findCourseArrangement(id);
    }

    @Override
    public EvaluateCourseStarInfo findEvaluation(String string) {
        EvaluateCourseStarInfo evaluateCourseStarInfo = new EvaluateCourseStarInfo();
        JSONObject jsonObject = JSONObject.fromObject(string);
        int courseId = jsonObject.getInt("courseId");
        int studentId = jsonObject.getInt("studentId");
        evaluateCourseStarInfo =courseMapper.findEvaluation(courseId,studentId);
        return evaluateCourseStarInfo;
    }


    private ArrayList<CourseInfo> getCourseListInfo(){
        ArrayList<CourseInfo> courseInfos = courseMapper.findAllCourse();
        return courseInfos;
    }


    private ArrayList<TeacherInfo> getTeacherListInfo(){
        ArrayList<TeacherInfo> teacherInfos = courseMapper.findAllTeacher();
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
