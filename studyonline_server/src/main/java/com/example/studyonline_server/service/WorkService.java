package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.MyWorkDTO;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.WorkInfo;

import java.util.ArrayList;

public interface WorkService {
    ArrayList<MyWorkDTO> findMyWork(String string);

    ResultInfo commitWork(WorkInfo workInfo);
}
