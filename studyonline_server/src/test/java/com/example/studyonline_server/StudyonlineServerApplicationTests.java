package com.example.studyonline_server;

import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.mapper.CourseListMapper;
import com.example.studyonline_server.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private CourseListMapper courseListMapper;
    @Test
    void findAll(){
        ArrayList<CourseListDTO> courseListDTOArrayList = courseService.getCourseList();
        for (CourseListDTO courseListDTO :courseListDTOArrayList){
            System.out.println(courseListDTO);
        }
    }


    @Test
    void findCourse(){
        System.out.println(courseListMapper.findById(2));
    }

    @Test
    void image() throws IOException {

        System.out.println(courseService.getCourseImg(2));
    }
}
