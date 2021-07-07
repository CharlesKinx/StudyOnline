package com.example.studyonline_server.controller.web;

import com.example.studyonline_server.model.TableTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {


    @GetMapping("/student")
    private String student(Model model){

        List<TableTest> tables = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            TableTest table = new TableTest();
            table.setId(i);
            table.setUsername("张" + (1+i));
            table.setPassword("password" + (1+i));
            table.setAge(new Random().nextInt(30));
            table.setAddress("杭州");
            if (i%2 ==0){
                table.setGender(0);
            }else {
                table.setGender(1);
            }

            table.setPhone(new Integer((1000000 +new Random().nextInt(500000))).toString());
            tables.add(table);
        }
        model.addAttribute("tables",tables);
        return "student";
    }

}
