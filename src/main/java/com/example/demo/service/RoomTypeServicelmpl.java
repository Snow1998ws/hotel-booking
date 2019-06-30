package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.mapper.RoomTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeServicelmpl implements RoomTypeService
{
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Override
    public RoomType findRoomTypeByid(Integer id)
    {
        return roomTypeMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<RoomType> findRoomTypeByRooms(List<Room> rooms)
    {
        List<RoomType> roomTypes=new ArrayList<>();
        for(int i=0;i<rooms.size();i++)
        {
            int roomtype_id=rooms.get(i).getrRoomtypeId();
            RoomType roomType=roomTypeMapper.selectByPrimaryKey(Integer.valueOf(roomtype_id));
            roomTypes.add(roomType);
        }
        return roomTypes;
    }
}
