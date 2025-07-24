package com.pierre.nockydelivery.delivery.traking.api.controller;

import com.pierre.nockydelivery.delivery.traking.domain.model.Delivery;
import com.pierre.nockydelivery.delivery.traking.domain.model.DeliveryInput;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput input) {

        return null;
    }

    @PutMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery edit(@RequestBody @Valid DeliveryInput input) {

        return null;
    }
}
