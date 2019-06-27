package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Room;
import com.example.demo.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServicelmpl implements RoomService{


    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Room findRoomByRoomid(Integer id)
    {
//        RoomExample roomExample=new RoomExample();
//        RoomExample.Criteria criteria=roomExample.createCriteria();
//        criteria.andRoomIdEqualTo(id);
        return roomMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Room> findRoomsByOrders(List<Orders> orders)
    {
        List<Room> rooms=new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            int room_id=orders.get(i).getoRoomId();
            Room room=roomMapper.selectByPrimaryKey(Integer.valueOf(room_id));
            rooms.add(room);
        }
        return rooms;
    }
}
