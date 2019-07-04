package com.example.demo.controller;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Orders;
import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.service.HotelService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomTypeService;
import net.sf.json.util.JSONUtils;
import org.apache.ibatis.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/")
public class OrderController {
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
        String user_id=(String) session.getAttribute("user_id");
        Order_info order_info = new Order_info();
        List<Order_info> order_infos=new ArrayList<>();
        List<Orders> orders=ordersService.findPre_ordersByid(user_id);
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        for(int i=0;i<orders.size();i++)
        {
            Hotel hotel = hotels.get(i);
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_info.setDiscount(orders.get(i).getDiscount());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_infos.add(order_info);
        }
        model.addAttribute("order_info",order_infos);
        return "present_booking";
    }

    @RequestMapping("/Order_Not_pay")
    public String Order_Not_pay(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        String user_id=(String) session.getAttribute("user_id");
        Order_info order_info = new Order_info();
        List<Order_info> order_infos=new ArrayList<>();
        List<Orders> orders=ordersService.findNotPay_orderByid(user_id);
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        for(int i=0;i<orders.size();i++)
        {
            Hotel hotel = hotels.get(i);
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_info.setDiscount(orders.get(i).getDiscount());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_infos.add(order_info);
        }
        model.addAttribute("order_Not_pay_info",order_infos);
        return "wait_for_pay_booking.html";
    }
    @RequestMapping("/order_bef_info")
    public String order_bef_info(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        String user_id=(String) session.getAttribute("user_id");
        Order_info order_info = new Order_info();
        List<Order_info> order_infos=new ArrayList<>();
        List<Orders> orders=ordersService.findBef_ordersByid(user_id);
        List<Room> rooms=roomService.findRoomsByOrders(orders);
        List<RoomType> roomTypes=roomTypeService.findRoomTypeByRooms(rooms);
        List<Hotel> hotels=hotelService.findHotelsByRooms(rooms);
        for(int i=0;i<orders.size();i++)
        {
            Hotel hotel = hotels.get(i);
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_info.setDiscount(orders.get(i).getDiscount());
            order_info.setPrice(orders.get(i).getTotalprice());
            order_info.setOrder_id(orders.get(i).getOrderId());
            order_info.setHotel_name(hotel.gethAddress());
            order_info.setTypename(roomTypes.get(i).getTypeName());
            order_infos.add(order_info);
        }
        model.addAttribute("order_bef_info",order_infos);
        return "history_booking";
    }

    @PostMapping("/deleteRoom")
    @ResponseBody
    public String deleteRoom(HttpServletRequest request, Model model)
    {
        String id=request.getParameter("infoss");
        Orders order=ordersService.findOrdersByOrder_id(Integer.valueOf(id));
        order.setIspay("o");//代表退款
        ordersService.deleteOrderByid(order);
        return id;
    }

    @PostMapping("/payRoom")
    @ResponseBody
    public String payRoom(HttpServletRequest request,Model model)
    {
        String id=request.getParameter("infoss");
        Orders order=ordersService.findOrdersByOrder_id(Integer.valueOf(id));
        order.setIspay("y");
        ordersService.UpdateOrder(order);
        return id;
    }

    @PostMapping("/viewOrder_info")
    @ResponseBody
    public String viewOrder_info(HttpServletRequest request,Model model)
    {
        String id=request.getParameter("infoss");
        Orders order=ordersService.findOrdersByOrder_id(Integer.valueOf(id));
        int roomid=order.getoRoomId();
        Room room=roomService.findRoomByRoomid(roomid);
        Integer hotelid=room.getrHotelId();
        return hotelid.toString();
    }

    @PostMapping("/delehistory")
    @ResponseBody
    public String delehistory(HttpServletRequest request,Model model)
    {
        String id=request.getParameter("infoss");
        Orders order=ordersService.findOrdersByOrder_id(Integer.valueOf(id));
        ordersService.deleteOrderByid(order);
        return id;
    }

}