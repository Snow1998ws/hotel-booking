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
    List<Room> findRoomsByDateAndHotelId(String checkin_time, String leave_time, Integer hotel_id);
    List<Room> findRoomsByDateAndHotelId2(String checkin_time, String leave_time, Integer hotel_id);
    void deleteRoomByRooms(Room room);
}
