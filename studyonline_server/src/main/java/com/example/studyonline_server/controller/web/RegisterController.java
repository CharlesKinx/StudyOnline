package com.example.studyonline_server.controller.web;

import com.example.studyonline_server.service.impl.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private AdministratorServiceImpl administratorService;


    @RequestMapping("/register")
    public String register(){

        return "register";
    }


    @PostMapping("/register")
    public String doRegister(
            @RequestParam(value = "account",required=false) String account,
            @RequestParam(value = "name",required=false) String name,
            @RequestParam(value = "telephone",required=false) String telephone,
            @RequestParam(value = "password",required=false) String password,
            Model model,
            HttpServletRequest request
    ){


        if(account == null || account.equals("")){
            model.addAttribute("error", "账号不能为空！");
            return "register";
        }
        if(administratorService.findByAccount(account)){
            model.addAttribute("error", "账号已存在！");
            return "register";
        }
        if(name == null || name.equals("")){
            model.addAttribute("error", "昵称不能为空！");
            return "register";
        }
        if(telephone == null || telephone.equals("")){
            model.addAttribute("error", "手机号不能为空！");
            return "register";
        }
        if(password == null || password.equals("")){
            model.addAttribute("error", "密码不能为空！");
            return "register";
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("user",administratorService.findByAccount(account));
            return administratorService.registerProcess(account,name,telephone,password,model);
        }


    }
}
