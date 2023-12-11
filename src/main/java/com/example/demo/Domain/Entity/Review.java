package com.example.demo.Domain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {

    @Id
    private int reviewId;
    private String userId;
    private int hotelId;
    private int reserveId;
    private String contents;

}