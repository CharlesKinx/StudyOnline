package com.example.studyonline_server.service.impl;


import com.example.studyonline_server.mapper.StudentMapper;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.service.AccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private StudentMapper studentMapper;



    public ResultInfo changeStudentInfo(StudentInfo studentInfo){
        ResultInfo resultInfo = new ResultInfo();

        studentMapper.updateStudentInfo(studentInfo);
        resultInfo.setSuccess(true);
        resultInfo.setData(studentMapper.findByAccount(studentInfo.getAccount()));
        resultInfo.setMsg("修改成功");

        return resultInfo;
    }

    public ResultInfo registerStudent(StudentInfo studentInfo){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(null);
        resultInfo.setSuccess(false);

        if(isExitAccount(studentInfo.getAccount())){
            resultInfo.setMsg("账号已存在");
        }else {
            studentMapper.registerStudent(studentInfo);
            studentInfo.setId(studentMapper.findIdByAccount(studentInfo.getAccount()));
            resultInfo.setMsg("注册成功");
            resultInfo.setSuccess(true);
            resultInfo.setData(studentInfo);
        }

        return resultInfo;
    }

    @Override
    public ResultInfo loginStudent(String loginInfo) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(null);
        resultInfo.setSuccess(false);
        JSONObject jsonObject = JSONObject.fromObject(loginInfo);

        if(!isExitAccount(jsonObject.getString("account"))){
            resultInfo.setMsg("账号不存在");
        }else if(!isRightPassword(jsonObject.getString("account"),jsonObject.getString("password"))){
            resultInfo.setMsg("密码不正确");
        }else{
            resultInfo.setSuccess(true);
            resultInfo.setMsg("登录成功");
            resultInfo.setData(studentMapper.findByAccount(jsonObject.getString("account")));
        }
        return resultInfo;
    }

    @Override
    public ResultInfo updateStudent(StudentInfo studentInfo) {
        return null;
    }


    private boolean isRightPassword(String account,String password){
        StudentInfo studentInfo1 = studentMapper.findByAccount(account);
        if(studentInfo1.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    private boolean isExitAccount(String account){
        StudentInfo studentInfo = studentMapper.findByAccount(account);
        if(studentInfo == null){
            return false;
        }else {
            return true;
        }
    }


}
