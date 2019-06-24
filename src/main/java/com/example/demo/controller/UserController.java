package com.example.demo.controller;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
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

