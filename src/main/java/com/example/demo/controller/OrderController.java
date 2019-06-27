package com.example.demo.controller;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Orders;
import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.service.OrdersService;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<Orders> orders=ordersService.findPre_ordersByid(Integer.valueOf(user_id));
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        List<Order_info> order_infos=new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            Order_info order_info=new Order_info();
            order_info.setDiscount(orders.get(i).getDiscount());
//            String tmp=hotels.get(i).gethAddress();
//            order_info.setHotel_name(tmp);
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_infos.add(order_info);

        }
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
        List<Orders> orders=ordersService.findBef_ordersByid(Integer.valueOf(user_id));
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
            order_info.setTypename(roomTypes.get(i).getTypeName());

            order_infos.add(order_info);
        }
        model.addAttribute("order_bef_info",order_infos);
        return "history_booking.html";
    }
    @RequestMapping("/Order_Not_pay")
    public String Order_Not_pay(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        String user_id=session.getAttribute("user_id").toString();
        List<Orders> orders=ordersService.findNotPay_orderByid(Integer.valueOf(user_id));
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
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_infos.add(order_info);
        }
        model.addAttribute("order_Not_pay_info",order_infos);
        return "wait_for_pay_booking.html";
    }

    @RequestMapping("/deleteRoom")
    public String deleteRoom(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        int order_id=((Order_info)session.getAttribute("orderinfo")).getOrder_id();
        
        return "order_pre_info";
    }

    @RequestMapping("/payRoom")
    public String payRoom(HttpServletRequest request,Model model)
    {
        return "payment";
    }

}
