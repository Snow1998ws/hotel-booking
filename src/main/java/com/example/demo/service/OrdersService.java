package com.example.demo.service;

import com.example.demo.domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdersService {
    Orders findOrdersByOrder_id(Integer id);//Order_id
    List<Orders> findPre_ordersByid(String id);
    List<Orders> findBef_ordersByid(String id);
    List<Orders> findNotPay_orderByid(String id);
    void deleteOrderByid(Orders orders);
}
