package com.usdev.domain.model;

import com.usdev.domain.shared.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Priority implements ValueObject<Priority> {

    private Integer id;

    @Override
    public boolean sameValueAs(Priority other) {
        return other != null && this.id.equals(other.id);
    }

}
