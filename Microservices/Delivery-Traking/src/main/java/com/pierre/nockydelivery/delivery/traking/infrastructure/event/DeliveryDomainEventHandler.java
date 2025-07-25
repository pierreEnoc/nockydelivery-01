package com.pierre.nockydelivery.delivery.traking.infrastructure.event;

import com.pierre.nockydelivery.delivery.traking.domain.event.DeliveryFulfilledEvent;
import com.pierre.nockydelivery.delivery.traking.domain.event.DeliveryPickUpEvent;
import com.pierre.nockydelivery.delivery.traking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {
    /**
     * Handles the DeliveryPlacedEvent.
     *
     * @param event the DeliveryPlacedEvent to handle
     */
    @EventListener
    public void handleDeliveryPlacedEvent(DeliveryPlacedEvent event) {
        log.info(event.toString());
        // Here you can add logic to handle the event, such as updating a database or sending a notification
    }

    @EventListener
    public void handleDeliveryPickUpEvent(DeliveryPickUpEvent event) {
        log.info(event.toString());
        // Here you can add logic to handle the event, such as updating a database or sending a notification
    }

    @EventListener
    public void handleDeliveryFulfilledEvent(DeliveryFulfilledEvent event) {
        log.info(event.toString());
        // Here you can add logic to handle the event, such as updating a database or sending a notification
    }
}
