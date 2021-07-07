package com.example.studyonline_server.controller;


import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")

public class UserAccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/register")
    public ResultInfo register(@RequestBody StudentInfo studentInfo){
        return accountService.registerStudent(studentInfo);
    }

    @PostMapping("/change")
    public ResultInfo change(@RequestBody StudentInfo studentInfo){
        return accountService.changeStudentInfo(studentInfo);
    }

    @PostMapping("/login")
    public ResultInfo login(@RequestBody String loginInfo){

        return accountService.loginStudent(loginInfo);
    }

}
