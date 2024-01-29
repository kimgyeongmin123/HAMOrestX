package com.example.demo.Controller;

import com.example.demo.Domain.Dto.UserDto;
import com.example.demo.Domain.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String join_post(UserDto dto) {
        log.info("POST /join "+dto);

        boolean isjoin = userService.joinMember(dto);

        if(!isjoin){
            return "login";
        }
        return "login";

    }


}