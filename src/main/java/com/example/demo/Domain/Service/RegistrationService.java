package com.example.demo.Domain.Service;

import com.example.demo.Domain.Dto.HotelDto;
import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    private String uploadDir = "c:\\HamoRestX\\";

    @Autowired
    private HotelRepository hotelRepository;

    public boolean hotelAdd(HotelDto dto) throws IOException {

        System.out.println("호텔을 추가하는 서비스 호출 완료");

//        Hotel hotel = new Hotel();

        Hotel hotel = HotelDto.dtoToEntity(dto);

        hotelRepository.save(hotel);

        System.out.println("이제 파일저장 시작 지금까지의 hotel : " + hotel);

        System.out.println("dto.getHotelId : " + hotel.getHotelId());
        //저장 폴더 지정
        String uploadPath = uploadDir + File.separator + hotel.getHotelId() + File.separator + hotel.getHotelName();

        System.out.println("업로드할 파일명 : " + uploadPath);
        File dir = new File(uploadPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        List<String> boardList = new ArrayList<>();

        for(MultipartFile file  : dto.getFiles())
        {
            System.out.println("--------------------");
            System.out.println("FILE NAME : " + file.getOriginalFilename());
            System.out.println("FILE SIZE : " + file.getSize() + " Byte");
            System.out.println("--------------------");
            boardList.add(file.getOriginalFilename());


            //파일객체 생성
            File fileobj = new File(uploadPath,file.getOriginalFilename());

            //업로드
            file.transferTo(fileobj);
        }

        hotel.setFiles(boardList);
        hotelRepository.save(hotel);

        return true;
    }
}