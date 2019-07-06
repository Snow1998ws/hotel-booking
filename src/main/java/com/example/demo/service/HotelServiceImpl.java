package com.example.demo.service;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.HotelExample;
import com.example.demo.domain.Room;
import com.example.demo.mapper.HotelMapper;
import com.example.demo.mapper.OrdersMapper;
import com.example.demo.mapper.RoomMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<String, Object> findHotel(int page, int rows) {
        Page pages = PageHelper.startPage(page, rows);
        List<Hotel> list = hotelMapper.selectByExample(new HotelExample());
        PageInfo<Hotel> pageInfo = new PageInfo<Hotel>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("pageinfo", pageInfo);
        return map;
    }

//    @Override
//    public List<Hotel> findHotelByDateAndCityAndRates(String city, Integer low, Integer high,  String checkin_time, String leave_time) {
//        return hotelMapper.selectByDateAndCityAndRates(city, low, high, checkin_time, leave_time);
//    }

    @Override
    public Map<String, Object> findHotelByDateAndCityAndRates(String city, Integer low, Integer high,  String checkin_time, String leave_time, int page, int rows) {
        Page pages = PageHelper.startPage(page, rows);
        List<Hotel> list = hotelMapper.selectByDateAndCityAndRates(city, low, high, checkin_time, leave_time);
        HotelExample hotelExample=new HotelExample();
        PageInfo<Hotel> pageInfo = new PageInfo<Hotel>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("pageinfo", pageInfo);
        return map;
    }
    @Override
    public void addHotel(Hotel hotel)
    {
        hotelMapper.insertSelective(hotel);
    }

    @Override
    public List<Hotel> findHotelByContent(String content)
    {
        HotelExample hotelExample=new HotelExample();
        hotelExample.or().andHAddressLike("%"+content+"%");
        hotelExample.or().andHCityEqualTo(content);
        if(isInt(content))
        {
            hotelExample.or().andHIdEqualTo(Integer.valueOf(content));
            hotelExample.or().andHRatesBetween(Integer.valueOf(content)-100,Integer.valueOf(content)+100);
            hotelExample.or().andHScoreGreaterThanOrEqualTo(Integer.valueOf(content));
            hotelExample.or().andHStarGreaterThanOrEqualTo(Integer.valueOf(content));
        }
        hotelExample.or().andHNameLike("%"+content+"%");
        hotelExample.or().andHOverviewLike("%"+content+"%");
        return hotelMapper.selectByExample(hotelExample);
    }

    @Override
    public void UpdateHotel(Hotel hotel)
    {
        hotelMapper.updateByPrimaryKeySelective(hotel);
    }


    @Override
    public void deleteHotelById(int hotelid)
    {
        hotelMapper.deleteByPrimaryKey(hotelid);
    }
    @Override
    public List<Hotel> findHotelSelective(Hotel hotel, Integer low, Integer high) {
        HotelExample hotelExample = new HotelExample();
        HotelExample.Criteria criteria = hotelExample.createCriteria();
        hotelExample.setOrderByClause("h_rates");
        criteria.andHRatesBetween(low, high);
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
     public boolean isInt(String s)
     {
        for(int i=0;i<s.length();i++)
        {
            if(!Character.isDigit(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
