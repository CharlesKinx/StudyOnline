package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.MyClassDTO;
import com.example.studyonline_server.model.ResultInfo;

import java.util.ArrayList;

public interface ClassService {
    ArrayList<MyClassDTO> findMyClass(int id);

    ResultInfo addClass(String result);
}
