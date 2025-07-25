package com.pierre.nockydelivery.delivery.traking.infrastructure.event;

import com.pierre.nockydelivery.delivery.traking.domain.event.DeliveryFulfilledEvent;
import com.pierre.nockydelivery.delivery.traking.domain.event.DeliveryPickUpEvent;
import com.pierre.nockydelivery.delivery.traking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.pierre.nockydelivery.delivery.traking.infrastructure.kafka.KafkaTopicConfig.DELIVERY_EVENT_TOPIC;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {
    private final IntegrationEventPublisher integrationEventPublisher;
    /**
     * Handles the DeliveryPlacedEvent.
     *
     * @param event the DeliveryPlacedEvent to handle
     */
    @EventListener
    public void handleDeliveryPlacedEvent(DeliveryPlacedEvent event) {
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(),
                DELIVERY_EVENT_TOPIC);
    }

    @EventListener
    public void handleDeliveryPickUpEvent(DeliveryPickUpEvent event) {
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(),
                DELIVERY_EVENT_TOPIC);
    }

    @EventListener
    public void handleDeliveryFulfilledEvent(DeliveryFulfilledEvent event) {
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(),
                DELIVERY_EVENT_TOPIC);
    }
}
