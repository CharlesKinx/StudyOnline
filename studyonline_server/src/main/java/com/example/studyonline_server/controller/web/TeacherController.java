package com.example.studyonline_server.controller.web;

import com.example.studyonline_server.dto.StudentInfoDTO;
import com.example.studyonline_server.dto.TeacherInfoDTO;
import com.example.studyonline_server.service.impl.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class TeacherController {
    @Autowired
    private AdministratorServiceImpl administratorService;

    @GetMapping("/teacher")
    private String student(Model model){

        ArrayList<TeacherInfoDTO> teacherInfoDTOArrayList = administratorService.findTeacherInfo();
        model.addAttribute("tables",teacherInfoDTOArrayList);
        return "teacher";
    }

}
