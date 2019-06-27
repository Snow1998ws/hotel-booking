package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.OrdersExample;
import com.example.demo.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrdersServicelmpl implements OrdersService{

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public List<Orders> findOrdersByid(Integer id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOrderIdEqualTo(id);
        return ordersMapper.selectByExample(ordersExample);
    }
    @Override
    public List<Orders> findPre_ordersByid(Integer id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(id);
        Date date=new Date();
        criteria.andCheckinTimeGreaterThanOrEqualTo(date);
        criteria.andIspayEqualTo("y");
        return ordersMapper.selectByExample(ordersExample);
    }
    @Override
    public List<Orders> findBef_ordersByid(Integer id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(id);
        Date date=new Date();
        criteria.andLeaveTimeLessThan(date);
        return ordersMapper.selectByExample(ordersExample);
    }
    @Override
    public  List<Orders> findNotPay_orderByid(Integer id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(id);
        criteria.andIspayEqualTo("n");
        return ordersMapper.selectByExample(ordersExample);
    }
}
