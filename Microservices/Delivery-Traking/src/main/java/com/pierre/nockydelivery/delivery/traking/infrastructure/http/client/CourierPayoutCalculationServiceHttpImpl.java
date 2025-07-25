package com.pierre.nockydelivery.delivery.traking.infrastructure.http.client;

import com.pierre.nockydelivery.delivery.traking.domain.service.CourierPayoutCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {


    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
       try {
           var courierPayoutResultModel = courierAPIClient.payoutCalculation(
                   new CourierPayoutCalculationInput(distanceInKm));
           return courierPayoutResultModel.getPayoutFee();
         } catch (ResourceAccessException e) {
                throw new GatewayTimeoutException(e);
       }catch (HttpServerErrorException | IllegalArgumentException e){
            throw new BadGatewayException(e);
       }
    }
}
