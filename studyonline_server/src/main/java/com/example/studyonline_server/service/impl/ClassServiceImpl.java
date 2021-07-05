package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.MyClassDTO;
import com.example.studyonline_server.mapper.ClassMapper;
import com.example.studyonline_server.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public ArrayList<MyClassDTO> findMyClass(int id) {
        return classMapper.findMyClass(id);
    }
}
