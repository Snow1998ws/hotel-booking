package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdersService {
    Orders findOrdersByOrder_id(Integer id);//Order_id
    List<Orders> findPre_ordersByid(String id);
    List<Orders> findBef_ordersByid(String id);
    List<Orders> findNotPay_orderByid(String id);
    void saveOrder(Orders order);
    void deleteOrderByid(Orders orders);
    void deleteOrderByUserId(String user_id);
    List<Orders> findOrdersByContent(String content);
    void UpdateOrder(Orders orders);
    void deleteOrderByOrderId(int id);
    void deleteOrderByRooms(Room rooms);
    void deleteOrderByRoomsId(int room_id);
}
