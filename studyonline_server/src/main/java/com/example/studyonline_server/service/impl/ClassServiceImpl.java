package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.MyClassDTO;
import com.example.studyonline_server.mapper.ClassMapper;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.service.ClassService;
import net.sf.json.JSONObject;
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

    @Override
    public ResultInfo addClass(String result) {

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(null);
        resultInfo.setSuccess(false);
        resultInfo.setData(null);
        MyClassDTO myClassDTO = new MyClassDTO();

        JSONObject jsonObject = JSONObject.fromObject(result);
        int classId = jsonObject.getInt("classId");
        int studentId = jsonObject.getInt("studentId");


        if(!isExistClass(classId)){
            resultInfo.setMsg("班课不存在!");
        }else if(isAddClass(classId,studentId)){
            resultInfo.setMsg("你已加入该班课！");
        }else{
            classMapper.enterClass(studentId,classId);
            resultInfo.setMsg("成功加入班课!");
            resultInfo.setSuccess(true);
            resultInfo.setData(classMapper.findClass(classId));
        }

        return resultInfo;
    }

    private boolean isExistClass(int classId){
        ArrayList<Integer> classList = classMapper.findClassList();
        for(Integer integer:classList){
            if(classId == integer){
                return true;
            }
        }

        return false;

    }

    private boolean isAddClass(int classId,int studentId){

        ArrayList<Integer> myClassId = classMapper.findMyClassList(studentId);
        for(Integer integer:myClassId){
            if (classId == integer){
                return true;
            }
        }

        return false;

    }
}
