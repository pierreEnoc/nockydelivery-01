package com.pierre.nockydelivery.delivery.traking.infrastructure.http.client;


import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/v1/couriers")
public interface CourierAPIClient {

    @PostExchange("/payout-calculation")
    @Retry(name = "Retry_CourierAPIClient_payoutCalculation")
    @CircuitBreaker(name = "CircuitBreaker_CourierAPIClient_payoutCalculation")
    CourierPayoutResultModel payoutCalculation(@RequestBody CourierPayoutCalculationInput input);
}
