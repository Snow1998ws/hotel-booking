package com.example.demo.controller;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Room;
import com.example.demo.service.HotelService;
import com.example.demo.service.RoomService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class hotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;

    @RequestMapping("/roombook/{h_id}/{checkin_time}/{leave_time}")
    public String roomBook(@PathVariable("h_id")Integer h_id,
                           @PathVariable("checkin_time")String checkin_time,
                           @PathVariable("leave_time") String leave_time,
                           Model model) {
        List<Room> rooms = roomService.findRoomsByHotelId(h_id);
        model.addAttribute("rooms", rooms);
        return "room_booking";
    }

    @RequestMapping(value = "/hotelinfo/{hotel_id}")
    public String hotelInfo(@PathVariable("hotel_id") int id, Model model) throws ParseException {
        Hotel hotel = findHotelById(id);
        model.addAttribute("hotel", hotel);
        return "hotel_info";
    }

    @RequestMapping(value = "/hotelsearch/{h_city}")
    @ResponseBody
    public List<Hotel> hotelSearch(@PathVariable("h_city") String city, Model model) {
        System.out.println(city);
        Hotel hotel = new Hotel();
        hotel.sethCity(city);
        List<Hotel> hotels = findHotel(hotel);
        return hotels;
    }

    @RequestMapping(value = "/hotelsearch/{h_city}/{checkin_time}/{leave_time}")
    @ResponseBody
    public List<Hotel> hotelSearch(@PathVariable("h_city") String city,
                                   @PathVariable("checkin_time") String checkin_time,
                                   @PathVariable("leave_time") String leave_time,
                                   Model model) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        List<Hotel> hotels = hotelService.findHotelByDateAndCity(city,
                dateformat.parse(checkin_time), dateformat.parse(leave_time));
        return hotels;
    }

    public Hotel findHotelById(int id) {
        return hotelService.findHotelById(id);
    }

    public List<Hotel> findHotel(Hotel hotel) {
        return hotelService.findHotelSelective(hotel);
    }
}
