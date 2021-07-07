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
import java.util.List;

@Controller
public class LoginController {


    @Autowired
    private AdministratorServiceImpl administratorService;

    @RequestMapping("/")
    public String login(){

        return "login";
    }

    @PostMapping("login")
    public String doLogin(
            @RequestParam(value = "account",required=false) String account,
            @RequestParam(value = "password",required=false) String password,
            Model model,
            HttpServletRequest request
    ){

        if(account == null || account.equals("")){
            model.addAttribute("error", "账号不能为空！");
            return "login";
        }
        if(password ==null|| password.equals("")){
            model.addAttribute("error", "账号不能为空！");
            return "login";
        }

        if(!administratorService.findByAccount(account)){
            model.addAttribute("error", "账号不存在！");
            return "login";
        }

        if(!administratorService.isRightPassword(account,password)){
            model.addAttribute("error", "密码不正确！");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user",administratorService.findByAccount(account));
        return "redirect:/index";
    }
}
