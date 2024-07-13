package com.usdev.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.usdev.application.service.PriceService;
import com.usdev.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.usdev.application.ports.spi.PricePersistance;

class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PricePersistance priceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPrices() {
        // Arrange
        List<Price> expectedPrices = Collections.singletonList(createPrice());
        when(priceRepository.getAll()).thenReturn(expectedPrices);

        // Act
        List<Price> actualPrices = priceService.getAllPrices();

        // Assert
        assertEquals(expectedPrices, actualPrices);
    }

    @Test
    void testGetPrice() {
        // Arrange
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;
        Price expectedPrice = createPrice();
        when(priceRepository.findPrices(appDate, productId, brandId)).thenReturn(Collections.singletonList(expectedPrice));

        // Act
        Optional<Price> actualPrice = priceService.getPrice(appDate, productId, brandId);

        // Assert
        assertTrue(actualPrice.isPresent());
        assertEquals(expectedPrice, actualPrice.get());
    }

    private Price createPrice() {
        return Price.builder()
                .priceId(PriceId.builder().id(1L).build())
                .brandId(BrandId.builder().id(2L).build())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .priceList(PriceList.builder().id(3L).build())
                .productId(ProductId.builder().id(4L).build())
                .priority(Priority.builder().id(1).build())
                .price(6.0)
                .cur(Currency.builder().cur("test").build())
                .build();
    }
}
