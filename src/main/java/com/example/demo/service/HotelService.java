package com.example.demo.service;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Room;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface HotelService {
    List<Hotel> findHotelSelective(Hotel hotel, Integer low, Integer high);
    Map<String, Object> findHotelByDateAndCityAndRates(String city, Integer low, Integer high, String checkin_time, String leave_time, int page, int rows);
//    List<Hotel> findHotelByDateAndCityAndRates(String city, Integer low, Integer high, String checkin_time, String leave_time);
    List<Hotel> findHotel();
    Map<String, Object> findHotel(int page, int rows);
    Hotel findHotelById(int id);
    List<Hotel> findHotelsByRooms(List<Room> rooms);
    List<Hotel> findHotelByContent(String content);
    void UpdateHotel(Hotel hotel);
    void deleteHotelById(int hotelid);
    void addHotel(Hotel hotel);
}