package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.MyWorkDTO;
import com.example.studyonline_server.mapper.WorkMapper;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.StudentWorkInfo;
import com.example.studyonline_server.model.WorkFileInfo;
import com.example.studyonline_server.model.WorkInfo;
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

    @Override
    public ResultInfo commitWork(WorkInfo workInfo) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg("提交成功");
        resultInfo.setSuccess(true);
        StudentWorkInfo studentWorkInfo = new StudentWorkInfo();
        studentWorkInfo.setWorkId(workInfo.getWorkId());
        studentWorkInfo.setStudentId(workInfo.getStudentId());
        studentWorkInfo.setCommitTime(workInfo.getCommitTime());
        studentWorkInfo.setStatus(1);
        studentWorkInfo.setContent(workInfo.getContent());
        if (workInfo.getFileName() ==null){
            studentWorkInfo.setFileId(0);
        }else{
            WorkFileInfo workFileInfo = new WorkFileInfo();
            workFileInfo.setName(workInfo.getFileName());
            workFileInfo.setType(workInfo.getFileType());
            workFileInfo.setUrl(workInfo.getUrl());
            workMapper.insertFile(workFileInfo);
            studentWorkInfo.setFileId(workFileInfo.getId());
        }

        workMapper.updateWork(studentWorkInfo);
        resultInfo.setData(null);
        return resultInfo;
    }
}
