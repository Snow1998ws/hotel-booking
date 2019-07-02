package com.example.demo.service;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Room;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface HotelService {
    List<Hotel> findHotelSelective(Hotel hotel);
    List<Hotel> findHotelByDateAndCityAndRates(String city, Integer price, Date checkin_time, Date leave_time);
    List<Hotel> findHotel();
    Hotel findHotelById(int id);
    List<Hotel> findHotelsByRooms(List<Room> rooms);
    List<Hotel> findHotelByContent(String content);
}