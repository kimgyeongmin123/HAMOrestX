package com.example.demo.Controller;

import com.example.demo.Domain.Dto.HotelDto;
import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class MasterController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/admin/adminMain")
    public String adminMain() {

        return "/admin/adminMain";

    }

    @GetMapping("/admin/hotelPermit")
    public String hotelPermit(Model model) {

        //허가안된 숙소 리스트 가져오기
        List<Hotel> hotelList = hotelService.getHotelNList();
        model.addAttribute("hotels", hotelList);

        return "/admin/hotelPermit";

    }

    @GetMapping("/admin/hotelInfo/{hotelId}")
    public String hotelInfo(@PathVariable("hotelId") int hotelId, Model model){

        System.out.println("관리자가 숙소승인 페이지에서 숙소상세 조회하는 페이지 겟 컨트롤러/숙소아이디 : "+ hotelId);

        Hotel hotel = hotelService.getOneHotel(hotelId);

        model.addAttribute("hotel", hotel);

        System.out.println(hotel);

        return "/admin/hotelInfo";
    }

    @PostMapping("/admin/hotelPermit/{hotelId}")
    public String permitY(@PathVariable("hotelId") int hotelId){

        System.out.println("관리자가 숙소를 승인하는 포스트 컨트롤러");

        hotelService.permitY(hotelId);

        return "/admin/hotelPermit";
    }
}