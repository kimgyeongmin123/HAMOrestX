package com.example.demo.Domain.Dto;

import com.example.demo.Domain.Entity.Hotel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HotelDto {
    private int hotelId;
    private String ownerId;
    private String hotelAddr;
    private String hotelInfo;
    private String hotelName;

    private MultipartFile[] files;

    private String permit;

    public static Hotel dtoToEntity(HotelDto dto){
        Hotel hotel = Hotel.builder()
                .hotelId(dto.getHotelId())
                .ownerId(dto.getOwnerId())
                .hotelAddr(dto.getHotelAddr())
                .hotelInfo(dto.getHotelInfo())
                .hotelName(dto.getHotelName())
                .permit(dto.getPermit())
                .build();

        return hotel;
    }
}