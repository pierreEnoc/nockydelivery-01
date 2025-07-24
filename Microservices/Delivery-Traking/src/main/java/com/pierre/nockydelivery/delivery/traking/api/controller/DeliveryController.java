package com.pierre.nockydelivery.delivery.traking.api.controller;

import com.pierre.nockydelivery.delivery.traking.domain.model.Delivery;
import com.pierre.nockydelivery.delivery.traking.domain.model.DeliveryInput;
import com.pierre.nockydelivery.delivery.traking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService deliveryPreparationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput input) {

        return deliveryPreparationService.draft(input);
    }

    @PutMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.OK)
    public Delivery edit(@PathVariable UUID deliveryId,
                         @RequestBody @Valid DeliveryInput input) {

        return deliveryPreparationService.edit(deliveryId, input);
    }
}
