package com.example.demo.Domain.Service;

import com.example.demo.Domain.Dto.HotelDto;
import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    //허가된 숙소목록 조회
    public List<Hotel> getHotelYList() {
        return hotelRepository.selectYhotel();
    }

    //허가안된 숙소목록 조회
    public List<Hotel> getHotelNList(){
        return hotelRepository.selectNhotel();
    }

    public Hotel getOneHotel(int hotelId){

        Hotel getOneHotel = hotelRepository.findById(hotelId);

        return getOneHotel;

    }

    @Transactional
    public void permitY(int hotelId){

        hotelRepository.updateY(hotelId);

    }
}