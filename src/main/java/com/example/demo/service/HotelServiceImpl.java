package com.example.demo.service;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.HotelExample;
import com.example.demo.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public List<Hotel> findHotel() {
        return hotelMapper.selectByExample(new HotelExample());
    }

    @Override
    public List<Hotel> findHotelSelective(Hotel hotel) {
        HotelExample hotelExample = new HotelExample();
        HotelExample.Criteria criteria = hotelExample.createCriteria();
        if (hotel.gethCity() != null)
            criteria.andHCityLike(hotel.gethCity());
        if (hotel.gethName() != null)
            criteria.andHNameLike(hotel.gethName());
        if (hotel.gethScore() != null)
            criteria.andHScoreEqualTo(hotel.gethScore());
        return hotelMapper.selectByExample(hotelExample);
    }

    @Override
    public Hotel findHotelById(int id) {
        return hotelMapper.selectByPrimaryKey(id);
    }
}
