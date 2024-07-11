package com.usdev.domain.model;

import com.usdev.domain.shared.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BrandId implements ValueObject<BrandId> {


    private Long id;

    @Override
    public boolean sameValueAs(BrandId other) {
        return other != null && this.id.equals(other.id);
    }

}


