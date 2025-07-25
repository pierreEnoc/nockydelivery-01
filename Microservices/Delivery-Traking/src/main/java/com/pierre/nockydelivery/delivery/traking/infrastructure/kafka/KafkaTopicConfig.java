package com.pierre.nockydelivery.delivery.traking.infrastructure.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static String DELIVERY_EVENT_TOPIC = "deliveries.v1.events";

    @Bean
    public NewTopic deliveryEventTopic() {
        return TopicBuilder.name(DELIVERY_EVENT_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
