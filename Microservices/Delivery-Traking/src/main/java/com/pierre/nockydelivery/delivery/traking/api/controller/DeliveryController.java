package com.pierre.nockydelivery.delivery.traking.api.controller;

import com.pierre.nockydelivery.delivery.traking.domain.model.CourierIdInput;
import com.pierre.nockydelivery.delivery.traking.domain.model.Delivery;
import com.pierre.nockydelivery.delivery.traking.domain.model.DeliveryInput;
import com.pierre.nockydelivery.delivery.traking.domain.repository.DeliveryRepository;
import com.pierre.nockydelivery.delivery.traking.domain.service.DeliveryCheckpointService;
import com.pierre.nockydelivery.delivery.traking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService deliveryPreparationService;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryCheckpointService deliveryCheckpointService;
    /**
     * Endpoint to draft a new delivery.
     *
     * @param input the delivery input data
     * @return the created Delivery object
     */
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

    @GetMapping
    public PagedModel<Delivery> finaAll(@PageableDefault Pageable pageable) {
        return  new PagedModel<>(
            deliveryRepository.findAll(pageable));
    }

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.complete(deliveryId);
    }

    @PostMapping("/{deliveryId}/placement")
    public void place(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.place(deliveryId);
    }

    @PostMapping("/{deliveryId}/pickups")
    public void pickup(@PathVariable UUID deliveryId,
                       @Valid @RequestBody CourierIdInput input) {
        deliveryCheckpointService.pickUp(deliveryId, input.getCourierId());
    }
}
