package com.pierre.nockydelivery.delivery.traking.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

    @NotBlank
    private String name;

    @NonNull
    @Min(1)
    private Integer quantity;

}
