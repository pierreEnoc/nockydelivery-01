package com.pierre.nockydelivery.delivery.traking.domain.repository;


import com.pierre.nockydelivery.delivery.traking.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository
        extends JpaRepository<Delivery, UUID> {
}

