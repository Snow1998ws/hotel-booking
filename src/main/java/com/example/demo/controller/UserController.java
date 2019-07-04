package com.example.demo.controller;
import com.example.demo.domain.Hotel;
import com.example.demo.domain.Orders;
import com.example.demo.domain.User;
import com.example.demo.service.HotelService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
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

    @GetMapping("/hotellist")
    @ResponseBody
    public Map<String, Object> homePage(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) {
        Map<String, Object> map = hotelService.findHotel(pageNum, 9);
        return map;
    }

    @RequestMapping("/home")
    public String homePage(Model model) {
        return "home";
    }

    @RequestMapping("/admin_search")
    public String adminPage(Model model) {
        return "admin_search";
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
            session.setAttribute("perm", perm);
            if(perm == 1)
                return "redirect:/home";
            else
                return "redirect:/admin_search";
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
            updateUser(user);
            Integer perm = (Integer)session.getAttribute("perm");
            System.out.println(perm);
            if (perm == 1)
                return "redirect:/home";
            else
                return "redirect:/admin_search";
        } catch (Exception e) {
            e.printStackTrace();
            return "错误";
        }
    }
    @RequestMapping("/updateHotelinfo")
    public String updateInfo(Hotel hotel, HttpServletRequest request)
    {
        try {
            hotelService.UpdateHotel(hotel);
            return "redirect:/admin_search";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "错误";
        }
    }
    @RequestMapping("/updateOrderinfo")
    public String updateInfo(Orders orders, HttpServletRequest request)
    {
        try {
            ordersService.UpdateOrder(orders);
            return "redirect:/admin_search";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "错误";
        }
    }
    @PostMapping("/delete_user")
    @ResponseBody
    public String deletUser(@RequestParam("userId")String user_id) {
        System.out.println(user_id);
        ordersService.deleteOrderByUserId(user_id);
        userService.deleteUserById(user_id);
        return "删除成功!";
    }

    @PostMapping("/delete_order")
    @ResponseBody
    public String delete_order(@RequestParam("orderId")String orderId) {
        System.out.println(orderId);
        ordersService.deleteOrderByOrderId(Integer.valueOf(orderId));
        return "删除成功!";
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
    public String adminUser(@PathVariable("res") String id, Model model)
    {
        User user=userService.findUserById(id);
        model.addAttribute("user",user);
        return "admin_user";
    }


    @RequestMapping(value = "/adminHotel/{res}")
    public String adminHotel(@PathVariable("res") int id, Model model)
    {
        Hotel hotel=hotelService.findHotelById(id);
        model.addAttribute("hotel",hotel);
        return "admin_hotel";
    }
    @RequestMapping(value = "/adminOrder/{res}")
    public String adminOrder(@PathVariable("res") int id, Model model)
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

}

