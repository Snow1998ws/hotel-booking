package com.example.demo.controller;

import com.example.demo.domain.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @RequestMapping("/roombook/{h_id}/{checkin_time}/{leave_time}")
    public String roomBook(@PathVariable("h_id")Integer h_id,
                           @PathVariable("checkin_time")String checkin_time,
                           @PathVariable("leave_time") String leave_time,
                           Model model){
        List<Room> rooms_empty = roomService.findRoomsByDateAndHotelId(checkin_time, leave_time, h_id);
        List<Room> rooms_full = roomService.findRoomsByDateAndHotelId2(checkin_time, leave_time, h_id);
        model.addAttribute("rooms_empty", rooms_empty);
        model.addAttribute("rooms_full", rooms_full);
        return "room_booking";
    }

    @RequestMapping("/roombook")
    public String roomBook() {
        return "room_booking";
    }
}
