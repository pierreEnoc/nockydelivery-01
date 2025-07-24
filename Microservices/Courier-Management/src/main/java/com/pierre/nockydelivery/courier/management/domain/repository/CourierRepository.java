package com.pierre.nockydelivery.courier.management.domain.repository;

import com.pierre.nockydelivery.courier.management.domain.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {

}
