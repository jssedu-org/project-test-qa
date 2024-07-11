package com.usdev.infrastructure.postgres.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "prices")
public class PriceEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Long priceList;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "price")
    private Double price;
    @Column(name = "currency")
    private String currency;
}