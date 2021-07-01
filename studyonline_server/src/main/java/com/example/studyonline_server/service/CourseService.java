package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.ResultInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface CourseService {
    ArrayList<CourseListDTO> getCourseList();
    CourseInfo getCourseInfo(int id);
    String getCourseImg(int id) throws IOException;
}
