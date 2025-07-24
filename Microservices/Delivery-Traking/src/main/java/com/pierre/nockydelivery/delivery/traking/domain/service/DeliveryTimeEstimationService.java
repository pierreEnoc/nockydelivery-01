package com.pierre.nockydelivery.delivery.traking.domain.service;

import com.pierre.nockydelivery.delivery.traking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
