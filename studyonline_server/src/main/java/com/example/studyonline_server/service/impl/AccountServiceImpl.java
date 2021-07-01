package com.example.studyonline_server.service.impl;


import com.example.studyonline_server.mapper.StudentMapper;
import com.example.studyonline_server.mapper.TeacherMapper;
import com.example.studyonline_server.model.ResultInfo;
import com.example.studyonline_server.model.StudentInfo;
import com.example.studyonline_server.model.TeacherInfo;
import com.example.studyonline_server.service.AccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;


    public ResultInfo changeStudentInfo(StudentInfo studentInfo){
        ResultInfo resultInfo = new ResultInfo();

        studentMapper.updateStudentInfo(studentInfo);
        resultInfo.setSuccess(true);
        resultInfo.setData(studentMapper.findByAccount(studentInfo.getAccount()));
        resultInfo.setMsg("修改成功");

        return resultInfo;
    }

    public ResultInfo registerStudent(StudentInfo userInfo){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(null);
        resultInfo.setSuccess(false);

        if(isExitAccount(userInfo.getAccount(),userInfo.getRole())){
            resultInfo.setMsg("账号已存在");
        }else if(userInfo.getRole() == 1){
            studentMapper.registerStudent(userInfo);
            userInfo.setId(studentMapper.findIdByAccount(userInfo.getAccount()));
            resultInfo.setMsg("注册成功");
            resultInfo.setSuccess(true);
            resultInfo.setData(userInfo);
        }else{

            TeacherInfo teacherInfo = new TeacherInfo();
            BeanUtils.copyProperties(userInfo,teacherInfo);
            teacherMapper.registerTeacher(teacherInfo);
            userInfo.setId(teacherMapper.findIdByAccount(teacherInfo.getAccount()));
            resultInfo.setMsg("注册成功");
            resultInfo.setSuccess(true);
            resultInfo.setData(teacherInfo);
        }

        return resultInfo;
    }

    @Override
    public ResultInfo loginStudent(String loginInfo) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(null);
        resultInfo.setSuccess(false);
        JSONObject jsonObject = JSONObject.fromObject(loginInfo);
        int role = Integer.parseInt(jsonObject.getString("role"));

        if(!isExitAccount(jsonObject.getString("account"),role)){
            resultInfo.setMsg("账号不存在");
        }else if(!isRightPassword(jsonObject.getString("account"),jsonObject.getString("password"),role)){
            resultInfo.setMsg("密码不正确");
        }else{
            resultInfo.setSuccess(true);
            resultInfo.setMsg("登录成功");
            if(role==1){
                resultInfo.setData(studentMapper.findByAccount(jsonObject.getString("account")));
            }else{
                resultInfo.setData(teacherMapper.findByAccount(jsonObject.getString("account")));

            }
        }

        return resultInfo;
    }

    @Override
    public ResultInfo updateStudent(StudentInfo studentInfo) {
        return null;
    }



    private boolean isRightPassword(String account,String password,int role){
        if(role == 1){
            StudentInfo studentInfo1 = studentMapper.findByAccount(account);
            if(studentInfo1.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }else{
            TeacherInfo teacherInfo = teacherMapper.findByAccount(account);
            if(teacherInfo.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }
    }

    private boolean isExitAccount(String account,int role){
        if(role == 1){
            StudentInfo studentInfo = studentMapper.findByAccount(account);
            if(studentInfo == null){
                return false;
            }else {
                return true;
            }
        }else{
            TeacherInfo teacherInfo = teacherMapper.findByAccount(account);
            if(teacherInfo == null){
                return false;
            }else {
                return true;
            }
        }

    }


}
