package com.example.demo.Controller;

import com.example.demo.Config.auth.PrincipalDetails;
import com.example.demo.Domain.Dto.HotelDto;
import com.example.demo.Domain.Dto.OwnerDto;
import com.example.demo.Domain.Dto.UserDto;
import com.example.demo.Domain.Service.OwnerService;
import com.example.demo.Domain.Service.RegistrationService;
import com.example.demo.Domain.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

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
    public void Get_hotelAdd(Authentication authentication){

        // 현재유저정보 가져오기
        PrincipalDetails principal = (PrincipalDetails)authentication.getPrincipal();

        System.out.println("principal: " + principal);
        System.out.println("principal.getUserDto().getUserName(): " + principal.getUserDto().getUserId());

    }

    @PostMapping("/hotelAdd")
    public String hotelAdd(HotelDto dto) throws IOException {

        System.out.println("호텔을 추가하는 컨트롤러 진입");

        boolean isadd = registrationService.hotelAdd(dto);

        return "owner/hotelManage";
    }

    @GetMapping("/owner/hotelManage")
    public void Get_hotelManage(){

    }


}