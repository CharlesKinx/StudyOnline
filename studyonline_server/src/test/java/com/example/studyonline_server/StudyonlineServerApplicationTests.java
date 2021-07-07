package com.example.studyonline_server;

import com.example.studyonline_server.dto.MyClassDTO;
import com.example.studyonline_server.dto.MyWorkDTO;
import com.example.studyonline_server.dto.StudentInfoDTO;
import com.example.studyonline_server.mapper.ClassMapper;
import com.example.studyonline_server.mapper.CommentMapper;
import com.example.studyonline_server.mapper.CourseMapper;
import com.example.studyonline_server.mapper.WorkMapper;
import com.example.studyonline_server.model.CourseArrangementInfo;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.EvaluateCourseStarInfo;
import com.example.studyonline_server.model.ScoreInfo;
import com.example.studyonline_server.service.AdministratorService;
import com.example.studyonline_server.service.impl.AdministratorServiceImpl;
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

    @Autowired
    private ClassMapper classMapper;


    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private AdministratorServiceImpl administratorService;

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

    @Test
    void findScore(){
        ArrayList<ScoreInfo> scoreInfos = courseMapper.findMaxCourseScore();
        for(ScoreInfo scoreInfo : scoreInfos){
            System.out.println(scoreInfo);
        }
    }


    @Test
    void findMyClass(){
        ArrayList<Integer> scoreInfos = classMapper.findMyClassList(14);
        for(Integer scoreInfo : scoreInfos){
            System.out.println(scoreInfo);
        }
    }

    @Test
    void findWork(){
        ArrayList<MyWorkDTO> myWorkDTOS = workMapper.findClassWork(14,1);
        for(MyWorkDTO scoreInfo : myWorkDTOS){
            System.out.println(scoreInfo);
        }
    }

    @Test
    void findClass(){
        MyClassDTO myClassDTO = classMapper.findClass(943619);
        System.out.println(myClassDTO);
    }


    @Test
    void number(){
        int num = commentMapper.findStudentCommentNumber(14);
        System.out.println(num);
    }

    @Test
    void student(){
        ArrayList<StudentInfoDTO> arrayList = administratorService.findStudentInfo();
        for (StudentInfoDTO studentInfoDTO : arrayList){
            System.out.println(studentInfoDTO);
        }
    }
}
