package com.pierre.nockydelivery.delivery.traking.domain.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Item {
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;

    @Setter(AccessLevel.PACKAGE)
    private Integer quantity;

    public static Item brandNew(String name, Integer quantity) {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setQuantity(quantity);
        return item;
    }
}
