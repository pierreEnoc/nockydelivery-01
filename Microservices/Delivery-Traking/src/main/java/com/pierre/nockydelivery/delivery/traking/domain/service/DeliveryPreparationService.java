package com.pierre.nockydelivery.delivery.traking.domain.service;

import com.pierre.nockydelivery.delivery.traking.domain.exception.DomainException;
import com.pierre.nockydelivery.delivery.traking.domain.model.*;
import com.pierre.nockydelivery.delivery.traking.domain.repository.DeliveryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPreparationService {

    private final DeliveryRepository deliveryRepository;

    private final DeliveryTimeEstimationService deliveryTimeEstimationService;
    private final CourierPayoutCalculationService courierPayoutCalculationService;

    @Transactional
    public Delivery draft(DeliveryInput input) {
        Delivery delivery = Delivery.draft();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    @Transactional 
    public Delivery edit(UUID deliveryId, DeliveryInput input) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DomainException("Delivery not found with id: " + deliveryId));
        delivery.removeItems();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    private void handlePreparation(DeliveryInput input, Delivery delivery) {
        ContactPointInput senderInput = input.getSender();
        ContactPointInput recipientInpunt = input.getRecipient();

        ContactPoint sender = ContactPoint.builder()
                .zipCode(senderInput.getZipCode())
                .street(senderInput.getStreet())
                .number(senderInput.getNumber())
                .complement(senderInput.getComplement())
                .name(senderInput.getName())
                .phone(senderInput.getPhone())
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode(recipientInpunt.getZipCode())
                .street(recipientInpunt.getStreet())
                .number(recipientInpunt.getNumber())
                .complement(recipientInpunt.getComplement())
                .name(recipientInpunt.getName())
                .phone(recipientInpunt.getPhone())
                .build();

        DeliveryEstimate estimate =  deliveryTimeEstimationService.estimate(sender, recipient);
        BigDecimal calculatedPayout = courierPayoutCalculationService.calculatePayout(estimate.getDistanceInKm());

        BigDecimal distanceFee = calculateDistanceFee(estimate.getDistanceInKm());

        var preparationsDetails = Delivery.PreparationDetails.builder()
                .recipient(recipient)
                .sender(sender)
                .expectedDeliveryTime(estimate.getEstimatedTime())
                .courierPayout(calculatedPayout)
                .distanceFee(distanceFee)
                .build();

        delivery.editPreparationDetails(preparationsDetails);

        for (ItemInput itemInput : input.getItems()) {
           delivery.addItem(itemInput.getName(), itemInput.getQuantity());
        }
    }
    private BigDecimal calculateDistanceFee(Double distanceInKm) {
        return new BigDecimal("3")
                .multiply(new BigDecimal(distanceInKm))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
