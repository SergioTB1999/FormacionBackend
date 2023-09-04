package com.bosonit.netflixeurekasergioserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaSergioServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaSergioServerApplication.class, args);
	}

}
