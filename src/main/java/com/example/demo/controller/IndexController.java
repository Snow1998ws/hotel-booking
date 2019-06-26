package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping("/home.html")
    public String toIndex() {
        return "home";
    }

    @RequestMapping("/")
    public String index(){
        return "forward:/home.html";
    }
}
