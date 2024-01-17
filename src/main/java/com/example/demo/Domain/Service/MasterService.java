package com.example.demo.Domain.Service;

import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Entity.User;
import com.example.demo.Domain.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {

    @Autowired
    private UserRepository userRepository;

    //유저정보 조회
    public List<User> userList() {
        return userRepository.userList();
    }

    //사업자정보 조회
    public List<User> ownerList() {
        return userRepository.ownerList();
    }
}
