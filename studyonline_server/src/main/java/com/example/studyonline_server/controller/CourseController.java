package com.example.studyonline_server.controller;

import com.example.studyonline_server.dto.CourseListDTO;
import com.example.studyonline_server.model.CourseArrangementInfo;
import com.example.studyonline_server.model.CourseInfo;
import com.example.studyonline_server.service.impl.CourseServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("course")

public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping("/list")
    @ResponseBody
    public ArrayList<CourseListDTO> getCourseList(){
        return courseService.getCourseList();
    }


    @PostMapping("/arrangement")
    public ArrayList<CourseArrangementInfo> getArrangement(@RequestBody String string){
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");

        return courseService.getCourseArrangement(id);
    }


    @PostMapping("/info")
    @ResponseBody
    public CourseInfo getCourseInfo(@RequestBody String string){
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/img")
    @ResponseBody
    public String getCourseImg(@RequestBody String string) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(string);
        int id = jsonObject.getInt("id");
        return courseService.getCourseImg(id);
    }

}
