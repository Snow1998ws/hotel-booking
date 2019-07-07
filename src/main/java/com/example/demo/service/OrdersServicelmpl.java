package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.OrdersExample;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
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
    public void deleteOrderByOrderId(int id)
    {
        ordersMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void deleteOrderByRooms(Room rooms)
    {
            int room_id=rooms.getRoomId();
            deleteOrderByRoomsId(room_id);
    }
    @Override
    public void deleteOrderByRoomsId(int room_id)
    {
        OrdersExample ordersExample=new OrdersExample();
        ordersExample.or().andORoomIdEqualTo(room_id);
        List<Orders> orders=ordersMapper.selectByExample(ordersExample);
        for(int i=0;i<orders.size();i++)
        {
            deleteOrderByid(orders.get(i));
        }
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
//        OrdersExample.Criteria criteria=ordersExample.createCriteria();
//        criteria.andOUserIdEqualTo(id);
        Date date=new Date();
//        criteria.andCheckinTimeGreaterThanOrEqualTo(date);
//        criteria.andIspayEqualTo("y");
        ordersExample.or().andCheckinTimeGreaterThan(date).andIspayEqualTo("y").andOUserIdEqualTo(id);
        List<Orders> orders=ordersMapper.selectByExample(ordersExample);
        return ordersMapper.selectByExample(ordersExample);
    }
    @Override
    public List<Orders> findBef_ordersByid(String id)
    {
        OrdersExample ordersExample=new OrdersExample();
        Date date=new Date();
        ordersExample.or().andOUserIdEqualTo(id).andIspayEqualTo("o");
        ordersExample.or().andOUserIdEqualTo(id).andLeaveTimeLessThanOrEqualTo(date);
//        OrdersExample.Criteria criteria=ordersExample.createCriteria();
//        OrdersExample.Criteria criteria1=ordersExample.createCriteria();
//        criteria.andOUserIdEqualTo(id);

//        criteria.andLeaveTimeLessThanOrEqualTo(date);
//        criteria1.andOUserIdEqualTo(id);
//        criteria1.andIspayEqualTo("o");
//        ordersExample.or(criteria1);
        List<Orders> orders=ordersMapper.selectByExample(ordersExample);
        return orders;
    }
    @Override
    public  List<Orders> findNotPay_orderByid(String id)
    {
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        criteria.andOUserIdEqualTo(id);
        criteria.andIspayEqualTo("n");
        Date date=new Date();
        criteria.andCheckinTimeGreaterThan(date);
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
