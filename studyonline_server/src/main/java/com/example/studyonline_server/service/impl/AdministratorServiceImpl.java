package com.example.studyonline_server.service.impl;

import com.example.studyonline_server.mapper.AdministratorMapper;
import com.example.studyonline_server.model.AdministratorInfo;
import com.example.studyonline_server.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

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


}
