package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.MyWorkDTO;
import com.example.studyonline_server.mapper.WorkMapper;
import com.example.studyonline_server.service.WorkService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    public ArrayList<MyWorkDTO> findMyWork(String string){
        JSONObject jsonObject = JSONObject.fromObject(string);
        int teacherId = jsonObject.getInt("teacherId");
        int studentId = jsonObject.getInt("studentId");

        return workMapper.findClassWork(studentId,teacherId);
    }
}
