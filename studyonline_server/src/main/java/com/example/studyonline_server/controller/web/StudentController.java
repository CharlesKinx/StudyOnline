package com.example.studyonline_server.controller.web;

import com.example.studyonline_server.dto.StudentInfoDTO;
import com.example.studyonline_server.mapper.StudentMapper;
import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.model.TableTest;
import com.example.studyonline_server.service.impl.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {

    @Autowired
    private AdministratorServiceImpl administratorService;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/student")
    private String student(Model model){

        ArrayList<StudentInfoDTO> studentInfoDTOS = administratorService.findStudentInfo();
        model.addAttribute("tables",studentInfoDTOS);
        return "student";
    }

    @RequestMapping("/delete")
    public String deleteStudent(@RequestParam(value = "account",required=false) String account ){
            System.out.println(account);
            studentMapper.deleteStudent(account);
            return "student";
    }

}
