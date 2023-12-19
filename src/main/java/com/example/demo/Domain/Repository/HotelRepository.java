package com.example.demo.Domain.Repository;

import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {

    //모든 숙소 조회
    List<Hotel> findAll();

    //허가가 된 숙소 조회
    @Query("SELECT H FROM Hotel H WHERE H.permit = 'Y'")
    List<Hotel> selectYhotel();

    //허가가 안된 숙소 조회
    @Query("SELECT H FROM Hotel H WHERE H.permit = 'N'")
    List<Hotel> selectNhotel();

    //hotelId로 숙소 하나 조회
    @Query("SELECT H FROM Hotel H WHERE H.hotelId = :hotelId")
    Hotel findById(@Param("hotelId") int hotelId);

    //숙소 허가 permit : Y 로 바꿈
    @Modifying
    @Query("UPDATE Hotel H SET H.permit = 'Y' WHERE H.hotelId = :hotelId")
    void updateY(@Param("hotelId") int hotelId);
}