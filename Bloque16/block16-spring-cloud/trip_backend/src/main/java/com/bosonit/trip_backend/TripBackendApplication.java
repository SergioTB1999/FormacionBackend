package com.bosonit.trip_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaClient
public class TripBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripBackendApplication.class, args);
	}

}
