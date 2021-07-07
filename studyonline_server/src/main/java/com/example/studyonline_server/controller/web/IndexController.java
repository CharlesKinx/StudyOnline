package com.example.studyonline_server.controller.web;

import com.example.studyonline_server.model.AdministratorInfo;
import com.example.studyonline_server.model.TableTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class IndexController {

    @GetMapping("/index")
        public String table(Model model){
        return "index";
    }


}
