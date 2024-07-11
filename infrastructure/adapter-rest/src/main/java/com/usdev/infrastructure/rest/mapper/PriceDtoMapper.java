package com.usdev.infrastructure.rest.mapper;

import com.usdev.domain.model.Price;
import com.usdev.infrastructure.rest.dto.PriceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") 
public interface PriceDtoMapper {
    
    @Mapping(source = "productId.id", target = "productId")
    @Mapping(source = "brandId.id", target = "brandId")
    @Mapping(source = "priceList.id", target = "priceList")
    PriceResponseDto toDto(Price prop);
}
