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
import java.util.ArrayList;
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

    @PostMapping(value = "/addHotel")
    public String addHotel(HttpServletRequest request,Model model)
    {
        Hotel hotel1=new Hotel();
        String hId=request.getParameter("hId");
        hotel1.sethCity(request.getParameter("hcity"));
        hotel1.sethAddress(request.getParameter("hAddress"));
        hotel1.sethLatitude(Double.valueOf(request.getParameter("hLatitude")));
        hotel1.sethScore(Integer.valueOf(request.getParameter("hScore")));
        hotel1.sethRates(Integer.valueOf(request.getParameter("hRates")));
        hotel1.sethLongtitude(Double.valueOf(request.getParameter("hLongtitude")));
        hotel1.sethName(request.getParameter("hname"));
        hotel1.sethTel(request.getParameter("htel"));
        hotel1.sethStar(Integer.valueOf(request.getParameter("hStar")));
        hotel1.sethPhoto1(request.getParameter("hPhoto1"));
        hotel1.sethPhoto1(request.getParameter("hPhoto2"));
        hotel1.sethPhoto1(request.getParameter("hPhoto3"));
        hotel1.sethPhoto1(request.getParameter("hPhoto4"));
        hotel1.sethPhoto1(request.getParameter("hPhoto5"));
        hotel1.sethOverview(request.getParameter("hOverview"));
        hotelService.addHotel(hotel1);
        return "admin_search";
    }

    @PostMapping(value = "/homeSearch")
    @ResponseBody
    public List<Hotel> homeSearch(HttpServletRequest request)
    {
        String page=request.getParameter("page");
        int pageNum=Integer.valueOf(page);
        String parame=request.getParameter("foss");
        List<Hotel> hotels=hotelService.findHotelByContent(parame);
        List<Hotel> hotels1=new ArrayList<>();
        for(int i=(pageNum-1)*9;i<hotels.size()&&i<pageNum*9;i++)
        {
            hotels1.add(hotels.get(i));
        }
        Hotel hotel=new Hotel();
        hotel.sethScore(hotels.size());
        hotels1.add(hotel);
        return hotels1;
    }
    @RequestMapping(value = "homesearchhref")
    public String homesearchhref(HttpServletRequest request)
    {
        return "home";
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
