package com.example.studyonline_server;

import com.example.studyonline_server.dto.CommentDTO;
import com.example.studyonline_server.mapper.CommentMapper;
import com.example.studyonline_server.mapper.CourseMapper;
import com.example.studyonline_server.model.CourseArrangementInfo;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.EvaluateCourseStarInfo;
import com.example.studyonline_server.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
class StudyonlineServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CommentMapper commentMapper;
    @Test
    void findAll(){
        ArrayList<CourseArrangementInfo> courseListDTOArrayList = courseMapper.findCourseArrangement(1);
        for (CourseArrangementInfo courseListDTO :courseListDTOArrayList){
            System.out.println(courseListDTO);
        }
    }


    @Test
    void findCourse(){
        System.out.println(courseMapper.findById(2));
    }

    @Test
    void image() throws IOException {

        System.out.println(courseService.getCourseImg(2));
    }

    @Test
    void findMyCourse(){
        ArrayList<CourseInfo> courseInfos =courseMapper.findMyCourse(14);
        for(CourseInfo courseInfo:courseInfos){
            System.out.println(courseInfo);
        }
    }

    @Test
    void findEvaluation(){
        EvaluateCourseStarInfo evaluateCourseStarInfo = courseMapper.findEvaluation(1,13);
        System.out.println(evaluateCourseStarInfo);
    }
}
