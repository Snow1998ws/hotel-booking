package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("")
public class Test {

    @Autowired

    @GetMapping("/")
    public String loginpage()
    {
        return "redirect:/login.html";
    }

    @RequestMapping("/home")
    public String Tohome()
    {
        return "home.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String username,
                        @RequestParam("psd") String password,
                        Map<String, Object> map){

        if(!StringUtils.isEmpty(username) && "123456".equals(password)){

            return "redirect:home";
        }

        map.put("msg", "用户名密码错误");
        return "redirect:login.html#0";
    }

}

