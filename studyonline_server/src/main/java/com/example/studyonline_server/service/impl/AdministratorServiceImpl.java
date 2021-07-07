package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.StudentInfoDTO;
import com.example.studyonline_server.mapper.*;
import com.example.studyonline_server.model.AdministratorInfo;
import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.service.AdministratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;


@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private WorkMapper workMapper;
    @Override
    public boolean findByAccount(String account) {
        AdministratorInfo administratorInfo = administratorMapper.findByAccount(account);
        if (administratorInfo!=null){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public String registerProcess(String account, String password,String name,String telephone, Model model) {


        AdministratorInfo administratorInfo = new AdministratorInfo();
        administratorInfo.setAccount(account);
        administratorInfo.setName(name);
        administratorInfo.setPassword(password);
        administratorInfo.setTelephone(telephone);

        administratorMapper.insertAdmin(administratorInfo);
        return "redirect:/";


    }

    public boolean isRightPassword(String account, String password){
        AdministratorInfo administratorInfo = administratorMapper.findByAccount(account);
        if(password.equals(administratorInfo.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<StudentInfoDTO> findStudentInfo() {

        ArrayList<StudentInfoDTO> studentInfoDTOArrayList = new ArrayList<>();

        ArrayList<StudentInfo> studentInfos = studentMapper.findAllStudent();
        for(StudentInfo studentInfo :studentInfos){
            StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
            BeanUtils.copyProperties(studentInfo,studentInfoDTO);

            studentInfoDTO.setClassNumber(classMapper.findStudentClassNumber(studentInfo.getId()));
            studentInfoDTO.setCommentNumber(commentMapper.findStudentCommentNumber(studentInfo.getId()));
            studentInfoDTO.setCourseNumber(classMapper.findStudentClassNumber(studentInfo.getId()));
            studentInfoDTO.setWorkNumber(workMapper.findStudentWorkNumber(studentInfo.getId()));
            studentInfoDTOArrayList.add(studentInfoDTO);
        }
        return studentInfoDTOArrayList;
    }


}
