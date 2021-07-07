package com.example.studyonline_server.controller.web;

import com.example.studyonline_server.dto.CourseInfoDTO;
import com.example.studyonline_server.dto.TeacherInfoDTO;
import com.example.studyonline_server.service.impl.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class WebCourseController {

    @Autowired
    private AdministratorServiceImpl administratorService;

    @GetMapping("/webCourse")
    private String student(Model model){

        ArrayList<CourseInfoDTO> courseInfoDTOArrayList = administratorService.findCourseInfoDTO();
        model.addAttribute("tables",courseInfoDTOArrayList);
        return "webCourse";
    }
}
