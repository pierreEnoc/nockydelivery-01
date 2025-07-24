package com.pierre.nockydelivery.delivery.traking.domain.repository;

import com.pierre.nockydelivery.delivery.traking.domain.model.ContactPoint;
import com.pierre.nockydelivery.delivery.traking.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createPreparationDetails());

        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

        assertEquals(2, persistedDelivery.getItems().size());

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