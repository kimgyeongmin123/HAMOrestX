package com.example.demo.Domain.Service;

import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    //모든 숙소목록 조회
    public List<Hotel> getHotelList() {
        return hotelRepository.findAll();
    }
}