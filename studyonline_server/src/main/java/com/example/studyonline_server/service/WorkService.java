package com.example.studyonline_server.service;

import com.example.studyonline_server.dto.MyWorkDTO;

import java.util.ArrayList;

public interface WorkService {
    ArrayList<MyWorkDTO> findMyWork(String string);
}
