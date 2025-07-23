package com.pierre.nockydelivery.delivery.traking.domain.model;

import com.pierre.nockydelivery.delivery.traking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


class DeliveryTest {

    @Test
    public  void shouldChangeToPlaced(){
        Delivery delivery = Delivery.draft();
        delivery.editPreparationDetails(createPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public  void shouldNotChangeToPlaced(){
        Delivery delivery = Delivery.draft();

       assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }

    private Delivery.PreparationDetails createPreparationDetails() {
       ContactPoint sender = ContactPoint.builder()
                .zipCode("12345-678")
                .street("Sender Street")
                .number("123")
                .complement("Apt 1")
                .name("Sender Name")
                .phone("1234567890")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("87654-321")
                .street("Recipient Street")
                .number("456")
                .complement("Apt 2")
                .name("Recipient Name")
                .phone("0987654321")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("10.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }

}