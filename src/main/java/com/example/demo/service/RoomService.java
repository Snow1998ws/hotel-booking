package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    Room findRoomByRoomid(Integer id);
    List<Room> findRoomsByHotelId(Integer id);
    List<Room> findRoomsByOrders(List<Orders> orders);
}
