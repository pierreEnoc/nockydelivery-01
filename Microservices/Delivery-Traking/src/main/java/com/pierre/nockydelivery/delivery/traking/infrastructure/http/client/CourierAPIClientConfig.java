package com.pierre.nockydelivery.delivery.traking.infrastructure.http.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CourierAPIClientConfig {

    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder()
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json");
    }

    @Bean
    public CourierAPIClient courierAPIClient(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl("http://courier-management").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(CourierAPIClient.class);
    }
}