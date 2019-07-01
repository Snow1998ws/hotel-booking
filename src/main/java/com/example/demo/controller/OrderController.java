package com.example.demo.controller;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Orders;
import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.service.HotelService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController
{
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping("/order_pre_info")
    public String orderpresent_info(HttpServletRequest request, Model model)
    {
        HttpSession session=request.getSession();
        String user_id=session.getAttribute("user_id").toString();
        List<Orders> orders=ordersService.findPre_ordersByid(user_id);
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        List<Order_info> order_infos=new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            Order_info order_info=new Order_info();
            order_info.setDiscount(orders.get(i).getDiscount());
            Hotel hotel=hotels.get(i);
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_infos.add(order_info);

        }
        session.setAttribute("info",order_infos);
        model.addAttribute("order_info",order_infos);
//        model.addAttribute("orders",orders);
//        model.addAttribute("roomTypes",roomTypes);
//        model.addAttribute("list",list);
        return "present_booking";
    }
    @ RequestMapping("/order_bef_info")
    public String Order_bef_info(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        String user_id=session.getAttribute("user_id").toString();
        List<Orders> orders=ordersService.findBef_ordersByid(user_id);
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        List<Order_info> order_infos=new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            Order_info order_info=new Order_info();
            order_info.setDiscount(orders.get(i).getDiscount());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setOrder_id(orders.get(i).getOrderId());
            Hotel hotel=hotels.get(i);
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_infos.add(order_info);
        }
        model.addAttribute("order_bef_info",order_infos);
        return "history_booking";
    }
    @RequestMapping("/Order_Not_pay")
    public String Order_Not_pay(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        String user_id=session.getAttribute("user_id").toString();
        List<Orders> orders=ordersService.findNotPay_orderByid(user_id);
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        List<Order_info> order_infos=new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            Order_info order_info=new Order_info();
            order_info.setDiscount(orders.get(i).getDiscount());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setOrder_id(orders.get(i).getOrderId());
            Hotel hotel=hotels.get(i);
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_infos.add(order_info);
        }
        model.addAttribute("order_Not_pay_info",order_infos);
        return "wait_for_pay_booking.html";
    }

    @RequestMapping("/deleteRoom")
    @ResponseBody
    public String deleteRoom(HttpServletRequest request, Model model)
    {
        String id=request.getParameter("infoss");

//        HttpSession session=request.getSession();
//        ArrayList tmp=(ArrayList)session.getAttribute("info");
//        //System.out.println(tmp.getClass());
//        Order_info info=(Order_info)tmp.get(Integer.valueOf(id));
//        Integer order_id=info.getOrder_id();
        Orders order=ordersService.findOrdersByOrder_id(Integer.valueOf(id));
        order.setIspay("o");//代表退款
        ordersService.deleteOrderByid(order);

        //model.addAttribute("order_bef_info",order_infos);
        return id;
    }

    @RequestMapping("/payRoom")
    public String payRoom(HttpServletRequest request,Model model)
    {
//        HttpSession session=request.getSession();
//        ArrayList tmp=(ArrayList)session.getAttribute("info");
//        //System.out.println(tmp.getClass());
//        Order_info info=(Order_info)tmp.get(0);
//        int order_id=info.getOrder_id();
//        Orders order=ordersService.findOrdersByOrder_id(order_id);
//        order.setIspay("o");//代表退款
//        ordersService.deleteOrderByid(order);
        return "payment.html";
    }

    @RequestMapping("/viewOrder_info")
    @ResponseBody
    public String viewOrder_info(HttpServletRequest request,Model model)
    {

        String id=request.getParameter("infoss");
        Orders order=ordersService.findOrdersByOrder_id(Integer.valueOf(id));
        Integer roomid=order.getoRoomId();
        Room room=roomService.findRoomByRoomid(roomid);
        String hotel_id=room.getrHotelId().toString();
        return hotel_id;
    }

}
