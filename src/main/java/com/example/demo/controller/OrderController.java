package com.example.demo.controller;

import com.example.demo.domain.Orders;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController
{
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/order_pre_info")
    public String orderpresent_info(HttpServletRequest request, Model model)
    {
        HttpSession session=request.getSession();
        String userid=(session.getAttribute("user_id").toString());
        List<Orders> orders=ordersService.findPre_ordersByid(Integer.valueOf(userid));
        for(int i=0;i<2&&i<orders.size();i++)
        {
            int tmp=i+1;
            model.addAttribute("order"+tmp,orders.get(i));
        }
        return "present_booking";
    }
}
