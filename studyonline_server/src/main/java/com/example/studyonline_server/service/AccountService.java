package com.example.studyonline_server.service;

import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.StudentInfo;

public interface AccountService {

    ResultInfo registerStudent(StudentInfo studentInfo);
    ResultInfo loginStudent(String loginInfo);
    ResultInfo updateStudent(StudentInfo studentInfo);
}
