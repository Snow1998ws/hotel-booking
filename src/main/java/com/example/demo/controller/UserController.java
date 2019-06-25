package com.example.demo.controller;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(User user) {
        System.out.println(user.getUserId());
        userService.saveUser(user);
        return "redirect:/home";
    }

    @RequestMapping
    public String homePage() {
        return "home";
    }

//    @RequestMapping("/search")
//    public List<User> findUser() {
//        return userService.findUser();
//    }
//
//    @RequestMapping("/add")
//    public String addUser(User user) {
//        userService.saveUser(user);
//        return "/user";
//    }
}

