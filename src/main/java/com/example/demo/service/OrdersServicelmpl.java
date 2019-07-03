package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.OrdersExample;
import com.example.demo.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrdersServicelmpl implements OrdersService{

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void saveOrder(Orders order){
        ordersMapper.insertSelective(order);
    }

    @Override
    public void deleteOrderByUserId(String user_id) {
        Orders order = new Orders();
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(user_id);
        ordersMapper.deleteByExample(ordersExample);
    }

    @Override
    public void UpdateOrder(Orders orders)
    {
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public List<Orders> findOrdersByContent(String content)
    {
        OrdersExample ordersExample=new OrdersExample();
        ordersExample.or().andIspayEqualTo(content);
        if(isInt(content))
        {
            ordersExample.or().andDiscountEqualTo(Integer.valueOf(content));
            ordersExample.or().andORoomIdEqualTo(Integer.valueOf(content));
            ordersExample.or().andOrderIdEqualTo(Integer.valueOf(content));
            ordersExample.or().andPeopleEqualTo(Integer.valueOf(content));
            ordersExample.or().andTotalpriceBetween(Integer.valueOf(content)-100,Integer.valueOf(content)+10);
        }

        ordersExample.or().andOUserIdEqualTo(content);
        return ordersMapper.selectByExample(ordersExample);
    }
    @Override
    public Orders findOrdersByOrder_id(Integer id)
    {
        return ordersMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Orders> findPre_ordersByid(String id)
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
    public List<Orders> findBef_ordersByid(String id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(id);
        Date date=new Date();
        criteria.andLeaveTimeLessThan(date);
        List<Orders> orders=ordersMapper.selectByExample(ordersExample);
        OrdersExample ordersExample1=new OrdersExample();
        OrdersExample.Criteria criteria11=ordersExample1.createCriteria();
        criteria11.andIspayEqualTo("o");
        List<Orders> orders1=ordersMapper.selectByExample(ordersExample1);
        orders.addAll(orders1);
        return orders;
    }
    @Override
    public  List<Orders> findNotPay_orderByid(String id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(id);
        criteria.andIspayEqualTo("n");
        return ordersMapper.selectByExample(ordersExample);
    }
    @Override
    public void deleteOrderByid(Orders orders)
    {
        ordersMapper.updateByPrimaryKey(orders);
    }
    public boolean isInt(String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if(!Character.isDigit(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
