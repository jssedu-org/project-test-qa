package com.usdev.application.ports.api;

import com.usdev.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PricesAPIService {

    Optional<Price> getPrice(LocalDateTime appDate, Long productID, Long brandId);

    List<Price> getAllPrices();
}
