package com.pierre.nockydelivery.delivery.traking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryTrakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryTrakingApplication.class, args);
	}

}
