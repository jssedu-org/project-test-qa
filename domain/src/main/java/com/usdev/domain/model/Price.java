package com.usdev.domain.model;

import com.usdev.domain.shared.Entity;
import com.usdev.domain.shared.RootAggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Price extends RootAggregate implements Entity<Price> {
    private PriceId priceId;
    private BrandId brandId;
    private LocalDateTime startDate;    
    private LocalDateTime endDate;
    private PriceList priceList;
    private ProductId productId;
    private Priority priority;
    private Double price;
    private Currency cur;

    @Override
    public boolean sameIdentityAs(Price other) {
        return other != null && this.priceId.sameValueAs(other.priceId);
    }
}
