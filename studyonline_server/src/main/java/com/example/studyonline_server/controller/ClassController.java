package com.example.studyonline_server.controller;


import com.example.studyonline_server.dto.MyClassDTO;
import com.example.studyonline_server.mapper.ClassMapper;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.service.impl.ClassServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("class")
public class ClassController {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassServiceImpl classService;

    @PostMapping("/list")
    @ResponseBody
    public ArrayList<MyClassDTO> getCourseInfo(@RequestBody String string){
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");
        return classMapper.findMyClass(id);
    }

    @PostMapping("/add")
    public ResultInfo addClass(@RequestBody String string){
        return classService.addClass(string);
    }
}
