package com.usdev.infrastructure.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PriceResponseDto {
    private Long productId;
    private Long brandId;
    private Long priceList;    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
}
