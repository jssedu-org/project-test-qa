package com.usdev.infrastructure.rest.controller;

import com.usdev.application.ports.api.PricesAPIService;
import com.usdev.domain.model.Price;
import com.usdev.infrastructure.rest.dto.PriceResponseDto;
import com.usdev.infrastructure.rest.mapper.PriceDtoMapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("/api/v1")
@Tag(name = "Prices", description = "Prices operations")
@Slf4j
public class PropResource {

        PricesAPIService apiService;

        PriceDtoMapper propMapper;

        @GetMapping("/prices/filter")
        @ApiResponse(responseCode = "200", description = "Price found for current input data", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = PriceResponseDto.class)))
        @ApiResponse(responseCode = "204", description = "No prices found for the current input parameters", content = @Content(mediaType = "application/json"))
        public ResponseEntity<PriceResponseDto> getPrices(@RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime appDate, @RequestParam Long productId,
                                                          @RequestParam Long brandId) {
                                
                log.debug("getPrices(date={},productId={},brandId={})", appDate, productId, brandId);
                
                Optional<Price> price = apiService.getPrice(appDate, productId, brandId);

                return price.map(c -> ResponseEntity.ok().body(propMapper.toDto(c)))
                            .orElse(ResponseEntity.noContent().build());                
        }

        @GetMapping("/prices")
        @ApiResponse(responseCode = "200", description = "Price found for current input data", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = PriceResponseDto.class)))
        public ResponseEntity<List<PriceResponseDto>> findAll() {

                log.debug("getPrices()");

                List<Price> prices = apiService.getAllPrices();

                return prices.stream().map(c -> propMapper.toDto(c)).collect(Collectors.collectingAndThen(Collectors.toList(), ResponseEntity::ok));
        }
}