package com.example.demo.controller;

import com.example.demo.domain.Hotel;
import com.example.demo.domain.Room;
import com.example.demo.service.HotelService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.RoomService;
import com.zaxxer.hikari.util.SuspendResumeLock;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class hotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/hotelinfo/{hotel_id}")
    public String hotelInfo(@PathVariable("hotel_id") int id, Model model) throws ParseException {
        Hotel hotel = findHotelById(id);
        model.addAttribute("hotel", hotel);
        return "hotel_info";
    }

    @RequestMapping(value = "/hotelsearch/{h_city}/{low}/{high}")
    @ResponseBody
    public List<Hotel> hotelSearch(@PathVariable("h_city") String city,
                                   @PathVariable("low") Integer low, @PathVariable("high")Integer high, Model model) {
        Hotel hotel = new Hotel();
        hotel.sethCity(city);
        List<Hotel> hotels = hotelService.findHotelSelective(hotel, low, high);
        return hotels;
    }

    @PostMapping("/delete_hotel")
    @ResponseBody
    public String deletUser(@RequestParam("hotelId")String hotelid) {
        System.out.println(hotelid);
        List<Room> rooms=roomService.findRoomsByHotelId(Integer.valueOf(hotelid));

        for(int i=0;i<rooms.size();i++)
        {
            ordersService.deleteOrderByRooms(rooms.get(i));
            roomService.deleteRoomByRooms(rooms.get(i));

        }
        hotelService.deleteHotelById(Integer.valueOf(hotelid));

        return "删除成功!";
    }

    @GetMapping(value = "/hotelsearch")
    @ResponseBody
    public Map<String, Object> hotelSearch(@RequestParam(value = "city", defaultValue = "")String city,
                           @RequestParam(value = "low", defaultValue = "0")Integer low,
                           @RequestParam(value = "high", defaultValue = "10000")Integer high,
                           @RequestParam(value = "checkin_time", defaultValue = "0000-00-00")String checkin_time,
                           @RequestParam(value = "leave_time",defaultValue = "0000-00-00")String leave_time,
                           @RequestParam(value = "pageNum", defaultValue = "1")Integer page) {
        return hotelService.findHotelByDateAndCityAndRates(city, low, high, checkin_time, leave_time, page, 9);
    }

//    @RequestMapping(value = "/hotelsearch/{h_city}/{low}/{high}/{checkin_time}/{leave_time}")
//    @ResponseBody
//    public List<Hotel> hotelSearch(@PathVariable("h_city") String city,
//                                   @PathVariable("low") Integer low,
//                                   @PathVariable("high") Integer high,
//                                   @PathVariable("checkin_time") String checkin_time,
//                                   @PathVariable("leave_time") String leave_time,
//                                   Model model) throws ParseException {
//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//        List<Hotel> hotels = hotelService.findHotelByDateAndCityAndRates(city, low, high,
//                dateformat.parse(checkin_time), dateformat.parse(leave_time));
//        return hotels;
//    }

    public Hotel findHotelById(int id) {
        return hotelService.findHotelById(id);
    }

}
