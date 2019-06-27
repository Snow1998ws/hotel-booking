package com.example.demo.service;

import com.example.demo.domain.Hotel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface HotelService {
    List<Hotel> findHotelSelective(Hotel hotel);
    List<Hotel> findHotel();
    Hotel findHotelById(int id);
}