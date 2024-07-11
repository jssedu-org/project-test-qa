package com.usdev.domain.model;

import com.usdev.domain.shared.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PriceList implements ValueObject<PriceList> {

    private Long id;

    @Override
    public boolean sameValueAs(PriceList other) {
        return other != null && this.id.equals(other.id);
    }

}
