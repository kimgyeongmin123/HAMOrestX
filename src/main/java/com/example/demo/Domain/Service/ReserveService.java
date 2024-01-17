package com.example.demo.Domain.Service;

import com.example.demo.Domain.Dto.ReserveDto;
import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Entity.Reserve;
import com.example.demo.Domain.Repository.HotelRepository;
import com.example.demo.Domain.Repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;


    public void bookReservation(int hotelId, String userId) {

        String uniqueRandomId = generateUniqueRandomId();

        Reserve reserve = new Reserve();
        reserve.setReserveId(uniqueRandomId);
        reserve.setHotelId(hotelId);
        reserve.setUserId(userId);
        reserveRepository.save(reserve);

    }

    private String generateUniqueRandomId() {
        String randomId;
        do {
            // 랜덤한 숫자 생성
            randomId = generateRandomId();
        } while (reserveRepository.existsByReserveId(randomId));

        return randomId;
    }

    private String generateRandomId() {
        // 원하는 길이의 랜덤한 숫자를 생성하는 코드
        int length = 5;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}