package com.example.studyonline_server;

import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class StudyonlineServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CourseServiceImpl courseService;

    @Test
    void findAll(){
        ArrayList<CourseListDTO> courseListDTOArrayList = courseService.getCourseList();
        for (CourseListDTO courseListDTO :courseListDTOArrayList){
            System.out.println(courseListDTO);
        }
    }
}
