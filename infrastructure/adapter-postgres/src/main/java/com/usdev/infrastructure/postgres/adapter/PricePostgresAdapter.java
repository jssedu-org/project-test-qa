package com.usdev.infrastructure.postgres.adapter;

import com.usdev.application.ports.spi.PricePersistance;
import com.usdev.domain.model.Price;
import com.usdev.infrastructure.postgres.mapper.PriceDboMapper;
import com.usdev.infrastructure.postgres.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class PricePostgresAdapter implements PricePersistance {

        PriceRepository pricesRepository;
        PriceDboMapper priceMapper;

        @Override
        public List<Price> getAll() {
            log.debug("getAll()");
            return pricesRepository.findAll().stream().map(p -> priceMapper.toDomain(p)).collect(Collectors.toList());
        }

        @Override
        public List<Price> findPrices(LocalDateTime appDate, Long productId, Long brandId) {
            log.debug("findPrices(appDate={},productId={},brandId={})", appDate, productId, brandId);
            return pricesRepository.findPrices(appDate, productId, brandId,
                                            Sort.by("priority").descending())
                                .stream().map(p -> priceMapper.toDomain(p)).collect(Collectors.toList());
        }
}
