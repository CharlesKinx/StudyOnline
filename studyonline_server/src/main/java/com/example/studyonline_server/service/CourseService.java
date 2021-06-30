package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.CourseListDTO;

import java.util.ArrayList;

public interface CourseService {
    ArrayList<CourseListDTO> getCourseList();
}
