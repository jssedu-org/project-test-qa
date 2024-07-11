package com.usdev.application.ports.spi;

import com.usdev.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistance {
    
    List<Price> getAll();

    List<Price> findPrices(LocalDateTime appDate, Long productId, Long brandId);    
}
