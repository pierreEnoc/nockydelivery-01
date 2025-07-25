package com.pierre.nockydelivery.delivery.traking.infrastructure.event;

public interface IntegrationEventPublisher {
    void publish(Object event,String key, String topic);
}
