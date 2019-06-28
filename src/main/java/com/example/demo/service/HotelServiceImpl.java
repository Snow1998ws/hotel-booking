package com.example.demo.service;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.HotelExample;
import com.example.demo.domain.Room;
import com.example.demo.mapper.HotelMapper;
import com.example.demo.mapper.OrdersMapper;
import com.example.demo.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Hotel> findHotel() {
        return hotelMapper.selectByExample(new HotelExample());
    }

    @Override
    public List<Hotel> findHotelByDateAndCity(String city, Date checkin_time, Date leave_time) {
        return hotelMapper.selectByDateAndCity(city, checkin_time, leave_time);
    }

    @Override
    public List<Hotel> findHotelSelective(Hotel hotel) {
        HotelExample hotelExample = new HotelExample();
        HotelExample.Criteria criteria = hotelExample.createCriteria();
        if (hotel.gethCity() != null)
            criteria.andHCityLike(hotel.gethCity());
        if (hotel.gethName() != null)
            criteria.andHNameLike(hotel.gethName());
        if (hotel.gethAddress() != null)
            criteria.andHAddressLike(hotel.gethAddress());
        if (hotel.gethScore() != null)
            criteria.andHScoreEqualTo(hotel.gethScore());
        if (hotel.gethOverview() != null)
            criteria.andHOverviewLike(hotel.gethOverview());
        return hotelMapper.selectByExample(hotelExample);
    }

    @Override
    public Hotel findHotelById(int id) {
        return hotelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Hotel> findHotelsByRooms(List<Room> rooms)
    {
        List<Hotel> hotels=new ArrayList<>();
        for(int i=0;i<rooms.size();i++)
        {
            int hotel_id=rooms.get(i).getrHotelId();
            Hotel hotel=hotelMapper.selectByPrimaryKey(hotel_id);
            hotels.add(hotel);
        }
        return hotels;
    }
}
