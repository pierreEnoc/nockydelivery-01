package com.pierre.nockydelivery.delivery.traking.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryInput {

    @NonNull
    @Valid
    private ContactPointInput sender;

    @NonNull
    @Valid
    private ContactPointInput recipient;

    @NotEmpty
    @Valid
    @Size(min = 1)
    private List<ItemInput> items;
}
