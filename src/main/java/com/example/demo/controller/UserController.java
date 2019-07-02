package com.example.demo.controller;
import com.example.demo.domain.Hotel;
import com.example.demo.domain.Orders;
import com.example.demo.domain.User;
import com.example.demo.service.HotelService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private OrdersService ordersService;
    @RequestMapping("/userinfo")
    public String inforPage(HttpServletRequest request, Model model) {
        /* ----------------------- 将用户信息存储进session -------------------- */
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        User user_find = findUserById(user_id);
        model.addAttribute("user", user_find);
        if (user_find.getGender() != null)
            model.addAttribute("gender", user_find.getGender().equals("m")? 0 : 1);
        return "user_info_change";
    }

    @RequestMapping("/home_page={current_page}")
    public String homePage(@PathVariable("current_page") Integer current_page, Model model) {
        System.out.println(current_page);
        List<Hotel> hotels = findHotel();
        int total_page = (int)Math.ceil((double)hotels.size() / 9);
        model.addAttribute("hotels", hotels.subList(9 * current_page - 9,
                9 * current_page > hotels.size() ? hotels.size(): 9 * current_page));
        model.addAttribute("current_page", current_page);
        model.addAttribute("total_page", total_page);
        return "home";
    }

    @RequestMapping("/home")
    public String homePage(Model model) {
        List<Hotel> hotels = findHotel();
        int total_page = (int)Math.ceil((double)hotels.size() / 9);
        model.addAttribute("hotels", hotels.subList(0,9));
        model.addAttribute("total_page", total_page);
        model.addAttribute("current_page", 1);
        return "home";
    }

    /*  ------------------------------------ 用户登出 ------------------------------------------   */
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user_id", null);
        session.setAttribute("psd", null);
        return "redirect:/home";
    }

    /*  ------------------------------------ 用户注册 ------------------------------------------   */
    @PostMapping("/signup")
    public String signUp(User user, HttpServletRequest request) {
        addUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user.getUserId());
        session.setAttribute("psd", user.getPsd());
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
            int perm=user_find.getPerm();
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getUserId());
            session.setAttribute("psd", user.getPsd());

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
            if(perm == 1)
                return "redirect:/home";
            else
                return "admin_search.html";
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


    @PostMapping("/admin_search")
    @ResponseBody
    public List<String> admin_search(HttpServletRequest request,Model model)
    {
        String list=request.getParameter("list");
        String content=request.getParameter("content");
        List<String> res=new ArrayList<>();
        if("0".equals(list))
        {
            List<User> users=userService.findUserByContent(content);
            for(int i=0;i<users.size();i++)
            {
                res.add(users.get(i).getUserId());
                res.add(users.get(i).getUserName());
            }
        }
        else if("1".equals(list))
        {
            List<Hotel> hotels=hotelService.findHotelByContent(content);
            for (int i=0;i<hotels.size();i++)
            {
                res.add(hotels.get(i).gethId().toString());
                res.add(hotels.get(i).gethName());
            }
        }
        else
        {
            List<Orders> orders=ordersService.findOrdersByContent(content);
            for(int i=0;i<orders.size();i++)
            {
                res.add(orders.get(i).getOrderId().toString());
                res.add(orders.get(i).getoRoomId().toString());
            }
        }
        return res;
    }

    @PostMapping("/adminManage")
    @ResponseBody
    public String adminManage(HttpServletRequest request , Model model)
    {
        String id=request.getParameter("foss");
        return id;
    }

    @RequestMapping(value = "/adminUser/{res}")
    public String adminUser(@PathVariable("res") String id, Model model) throws ParseException
    {
        User user=userService.findUserById(id);
        model.addAttribute("user1",user);
        return "admin_user";
    }

    @RequestMapping(value = "/adminHotel/{res}")
    public String adminHotel(@PathVariable("res") int id, Model model) throws ParseException
    {
        Hotel hotel=hotelService.findHotelById(id);
        model.addAttribute("hotel",hotel);
        return "admin_hotel";
    }
    @RequestMapping(value = "/adminOrder/{res}")
    public String adminOrder(@PathVariable("res") int id, Model model) throws ParseException
    {
        Orders orders=ordersService.findOrdersByOrder_id(id);
        model.addAttribute("order",orders);
        return "admin_booking";
    }

    public List<User> findUser() {
        return userService.findUser();
    }

    public User findUserById(String id) {
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

    public List<Hotel> findHotel() {
        return hotelService.findHotel();
    }

    public List<Hotel> findHotel(Hotel hotel) {
        return hotelService.findHotelSelective(hotel);
    }


}

