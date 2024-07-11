package com.usdev.infrastructure.postgres.mapper;

import com.usdev.domain.model.Price;
import com.usdev.infrastructure.postgres.entity.PriceEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceDboMapper {    

    @Mapping(source = "priceId.id", target = "id")
    @Mapping(source = "brandId.id", target = "brandId")
    @Mapping(source = "priceList.id", target = "priceList")
    @Mapping(source = "priority.id", target = "priority")
    @Mapping(source = "cur.cur", target = "currency")
    @Mapping(source = "productId.id", target = "productId")
    PriceEntity toDbo(Price domain);

    @InheritInverseConfiguration    
    Price toDomain(PriceEntity entity);

}