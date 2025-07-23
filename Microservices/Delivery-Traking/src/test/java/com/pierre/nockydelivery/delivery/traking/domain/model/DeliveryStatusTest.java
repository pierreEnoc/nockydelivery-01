package com.pierre.nockydelivery.delivery.traking.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    @Test
    void draftToWaitingForCourier() {
        DeliveryStatus current = DeliveryStatus.DRAFT;
        DeliveryStatus newStatus = DeliveryStatus.WAITING_FOR_COURIER;

        assertTrue(current.canChangeTo(newStatus));
        assertFalse(current.canNotChangeTo(newStatus));
    }

    @Test
    void draftToInTransit() {
        DeliveryStatus current = DeliveryStatus.DRAFT;
        DeliveryStatus newStatus = DeliveryStatus.IN_TRANSIT;

        assertFalse(current.canChangeTo(newStatus));
        assertTrue(current.canNotChangeTo(newStatus));
    }

}