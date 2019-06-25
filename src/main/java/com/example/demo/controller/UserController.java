package com.example.demo.controller;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userinfo")
    public String inforPage(HttpServletRequest request, Model model) {
        /* ----------------------- 将用户信息存储进session -------------------- */
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        List<User> users = findUserById(user_id);
        User user_find = users.get(0);
        model.addAttribute("tel", user_find.getTel());
        model.addAttribute("birth", user_find.getBirth());
        model.addAttribute("perm", user_find.getPerm());
        model.addAttribute("gender", user_find.getGender().equals("m")? 0 : 1);
        model.addAttribute("mail", user_find.getMail());
        model.addAttribute("nick", user_find.getNick());
        model.addAttribute("city", user_find.getCity());
        return "user_info_change";
    }

    @RequestMapping("/home")
    public String homePage() {
        return "home";
    }

    /*  ------------------------------------ 用户注册 ------------------------------------------   */
    @PostMapping("/signup")
    public String signUp(User user) {
        addUser(user);
        return "redirect:/home";
    }

    /*  ------------------------------------ 用户登陆 ------------------------------------------   */
    @PostMapping("/signin")
    public String signIn(User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = findUserByIdAndPsd(user);
            if (users.size() == 0) {
                return "账号或密码错误";
            }
            /* ------------------------------- 将用户信息存储进session --------------------------- */
            User user_find = users.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getUserId());
            session.setAttribute("psd", user.getPsd());
//            session.setAttribute("tel", user_find.getTel());
//            session.setAttribute("perm", user_find.getPerm());
//            session.setAttribute("mail", user_find.getMail());
//            session.setAttribute("city", user_find.getCity());
//            session.setAttribute("birth", user_find.getBirth());
//            session.setAttribute("nick", user_find.getNick());
//            session.setAttribute("gender", user_find.getGender());

            String result = "用户 "+ user.getUserId() + " 已登录";
            /*----------------------------- 获取并更新 cookie -----------------------------------*/
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie: cookies) {
//                    if("last".equals(cookie.getName())) {
//                        long time = Long.parseLong(cookie.getValue());
//                        Date date = new Date();
//                        date.setTime(time);
//                        result += "\n 上一次访问时间是:" + date.toLocaleString();
//                    }
//                }
//            }
//            System.out.println(result);
//            String time = System.currentTimeMillis()+"";
//            Cookie cookie = new Cookie("last" , time);
//            cookie.setMaxAge(60 * 60 * 24 * 7);
//            response.addCookie(cookie);
            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "数据库查询错误";
        }
    }

    /*  ------------------------------------- 更新用户 -------------------------------------------   */
    @RequestMapping("/updateinfo")
    public String updateInfo(User user, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if ("0".equals(user.getGender())) user.setGender("m");
            else user.setGender("f");
            user.setUserId((String)session.getAttribute("user_id"));
            updateUser(user);
            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "错误";
        }
    }


    public List<User> findUser() {
        return userService.findUser();
    }

    public List<User> findUserById(String id) {
        return userService.findUserById(id);
    }

    public List<User> findUserByIdAndPsd(User user) {
        return userService.findUserByIdAndPsd(user);
    }

    public void addUser(User user) {
        userService.saveUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }
}

