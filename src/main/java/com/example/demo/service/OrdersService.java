package com.example.demo.service;

import com.example.demo.domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdersService {
    List<Orders> findOrdersByid(Integer id);
    List<Orders> findPre_ordersByid(Integer id);
}
