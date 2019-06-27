package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomTypeService
{

    RoomType findRoomTypeByid(Integer id);
    List<RoomType> findRoomTypeByRooms(List<Room> rooms);

}
