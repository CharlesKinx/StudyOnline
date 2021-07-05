package com.example.studyonline_server.controller;

import com.example.studyonline_server.dto.MyWorkDTO;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.WorkInfo;
import com.example.studyonline_server.service.impl.WorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("work")

public class HomeWorkController {

    @Autowired
    private WorkServiceImpl workService;

    @PostMapping("/list")
    public ArrayList<MyWorkDTO> findMyWork(@RequestBody String string){
        return workService.findMyWork(string);
    }

    @PostMapping("/upload")
    public ResultInfo commitWork(@RequestBody WorkInfo workInfo){
        return workService.commitWork(workInfo);
    }

}
