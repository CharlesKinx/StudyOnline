package com.example.studyonline_server.controller;


import com.example.studyonline_server.dto.MyClassDTO;
import com.example.studyonline_server.mapper.ClassMapper;
import com.example.studyonline_server.model.CourseInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("class")
public class ClassController {

    @Autowired
    private ClassMapper classMapper;

    @PostMapping("/list")
    @ResponseBody
    public ArrayList<MyClassDTO> getCourseInfo(@RequestBody String string){
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");
        return classMapper.findMyClass(id);
    }
}
