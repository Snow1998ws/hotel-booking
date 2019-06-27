package com.example.demo.controller;

import com.example.demo.domain.Hotel;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class hotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/hotelinfo/{hotel_id}")
    public String hotelInfo(@PathVariable("hotel_id") int id, Model model) {
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

    public Hotel findHotelById(int id) {
        return hotelService.findHotelById(id);
    }

    public List<Hotel> findHotel(Hotel hotel) {
        return hotelService.findHotelSelective(hotel);
    }


}
