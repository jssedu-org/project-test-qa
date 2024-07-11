package com.usdev.domain.model;

import com.usdev.domain.shared.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Currency implements ValueObject<Currency> {

    private String cur;

    @Override
    public boolean sameValueAs(Currency other) {
        return other != null && this.cur.equals(other.cur);
    }
}
