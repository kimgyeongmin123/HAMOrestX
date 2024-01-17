package com.example.demo.Controller;

import com.example.demo.Config.auth.PrincipalDetails;
import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Service.HotelService;
import com.example.demo.Domain.Service.ReserveService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/reservation/{hotelId}")
    public String reservation(@PathVariable("hotelId") int hotelId, Model model, Authentication authentication){

        System.out.println("GET /reservation/"+hotelId);

        //hotelId 에 대한 hotel 정보 받아오기
        Hotel hotel = hotelService.getOneHotel(hotelId);

        // 현재 로그인한 사용자 정보 가져오기
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String userId = principal.getUsername();

        System.out.println("hotelId: " + hotelId);
        System.out.println("userId: " + userId);
        System.out.println("hotel: " + hotel);

        model.addAttribute("hotel",hotel);
        model.addAttribute("userId",userId);

        return "reservation";
    }

    @PostMapping("/reservation/{hotelId}")
    @ResponseBody
    public String makeReservation(@PathVariable("hotelId") int hotelId, @RequestParam("userId") String userId) {
        reserveService.bookReservation(hotelId, userId);
        return "Reservation successful for Hotel ID: " + hotelId + ", User ID: " + userId;
    }

}