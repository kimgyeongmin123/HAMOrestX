package com.example.demo.Controller;

import com.example.demo.Domain.Dto.HotelDto;
import com.example.demo.Domain.Dto.OwnerDto;
import com.example.demo.Domain.Dto.UserDto;
import com.example.demo.Domain.Service.OwnerService;
import com.example.demo.Domain.Service.RegistrationService;
import com.example.demo.Domain.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/ownerLogin")
    public void ownerLogin(){

        log.info("GET /ownerLogin");
    }

    @GetMapping("/ownerMain")
    public void ownerMain(){

    }
    @GetMapping("/owner/hotelAdd")
    public void Get_hotelAdd(){

    }

    @PostMapping("/hotelAdd")
    public String hotelAdd(HotelDto dto){

        System.out.println("호텔을 추가하는 컨트롤러 진입");

        boolean isadd = registrationService.hotelAdd(dto);

        return "owner/hotelManage";
    }

    @GetMapping("/owner/hotelManage")
    public void Get_hotelManage(){

    }


}