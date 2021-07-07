package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.StudentInfoDTO;
import com.example.studyonline_server.model.AdministratorInfo;
import org.springframework.ui.Model;

import java.util.ArrayList;

public interface AdministratorService {
    boolean findByAccount(String account);
    String registerProcess(String account, String password,String name,String telephone, Model model);
    boolean isRightPassword(String account, String password);

    ArrayList<StudentInfoDTO> findStudentInfo();
}
