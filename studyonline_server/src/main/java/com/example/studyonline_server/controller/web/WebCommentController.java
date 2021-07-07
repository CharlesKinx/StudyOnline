package com.example.studyonline_server.controller.web;


import com.example.studyonline_server.dto.CommentInfoDTO;
import com.example.studyonline_server.dto.CourseInfoDTO;
import com.example.studyonline_server.service.impl.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;



@Controller
public class WebCommentController {

    @Autowired
    private AdministratorServiceImpl administratorService;

    @GetMapping("/webComment")
    private String student(Model model){

        ArrayList<CommentInfoDTO> courseInfoDTOArrayList = administratorService.findCommentInfoDTO();
        model.addAttribute("tables",courseInfoDTOArrayList);
        return "webComment";
    }
}
