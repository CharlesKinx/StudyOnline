package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.dto.*;
import com.example.studyonline_server.mapper.*;
import com.example.studyonline_server.model.*;
import com.example.studyonline_server.service.AdministratorService;
import org.apache.ibatis.annotations.Select;
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

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;
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
            studentInfoDTO.setCourseNumber(courseMapper.findStudentCourseNumber(studentInfo.getId()));
            studentInfoDTO.setWorkNumber(workMapper.findStudentWorkNumber(studentInfo.getId()));
            studentInfoDTOArrayList.add(studentInfoDTO);
        }
        return studentInfoDTOArrayList;
    }

    @Override
    public ArrayList<TeacherInfoDTO> findTeacherInfo() {
        ArrayList<TeacherInfoDTO> teacherInfoDTOArrayList = new ArrayList<>();
        ArrayList<TeacherInfo> TeacherInfos = teacherMapper.findAllTeacher();
        for (TeacherInfo teacherInfo:TeacherInfos){
            TeacherInfoDTO teacherInfoDTO = new TeacherInfoDTO();
            BeanUtils.copyProperties(teacherInfo,teacherInfoDTO);
            teacherInfoDTO.setClassName(teacherMapper.findClassName(teacherInfo.getId()));
            teacherInfoDTO.setCourseName(teacherMapper.findCourseName(teacherInfo.getId()));
            teacherInfoDTO.setPublishWorkNum(teacherMapper.findWorkNum(teacherInfo.getId()));

            teacherInfoDTOArrayList.add(teacherInfoDTO);

        }
        return teacherInfoDTOArrayList;
    }

    @Override
    public ArrayList<CommentInfoDTO> findCommentInfoDTO() {
        ArrayList<CommentInfoDTO> commentInfoDTOArrayList = new ArrayList<>();


        ArrayList<CommentInfo> findAllComments = commentMapper.findAllComments();
        for(CommentInfo commentInfo :findAllComments){
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            BeanUtils.copyProperties(commentInfo,commentInfoDTO);
            commentInfoDTO.setCourseName(courseMapper.findCourseName(commentInfo.getId()));
            commentInfoDTO.setStudentName(studentMapper.findNameById(commentInfo.getStudentId()));

            commentInfoDTOArrayList.add(commentInfoDTO);
        }


        return commentInfoDTOArrayList;
    }

    @Override
    public ArrayList<CourseInfoDTO> findCourseInfoDTO() {
        ArrayList<CourseInfoDTO> courseInfoDTOArrayList = new ArrayList<>();
        ArrayList<CourseInfo> courseInfos = courseMapper.findAllCourse();
        for (CourseInfo courseInfo:courseInfos){
            CourseInfoDTO courseInfoDTO = new CourseInfoDTO();
            BeanUtils.copyProperties(courseInfo,courseInfoDTO);
            courseInfoDTO.setTeacherName(teacherMapper.findTeacherName(courseInfo.getTeacherId()));
            courseInfoDTO.setCourseNumber(courseMapper.findCourseNumber(courseInfo.getId()));
            courseInfoDTOArrayList.add(courseInfoDTO);
        }

        return courseInfoDTOArrayList;
    }

    @Override
    public ArrayList<WorkInfoDTO> findWorkInfoDTO() {
        ArrayList<WorkInfoDTO> workInfoDTOArrayList = new ArrayList<>();
        ArrayList<Work> works = workMapper.findAllWork();

        for(Work work:works){
            WorkInfoDTO workInfoDTO = new WorkInfoDTO();
            BeanUtils.copyProperties(work,workInfoDTO);

            workInfoDTO.setTeacherName(teacherMapper.findTeacherName(work.getTeacherId()));
            int commitNum = workMapper.findCommitNumber(work.getWork_id());
            workInfoDTO.setCommitNumber(commitNum);
            int classId = classMapper.findClassId(work.getTeacherId());
            workInfoDTO.setUnCommitNumber(classMapper.findAddClassNumber(classId) -commitNum );

            workInfoDTOArrayList.add(workInfoDTO);
        }
        return workInfoDTOArrayList;
    }


}
