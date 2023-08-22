package com.Bosonit.block7crudvalidation2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
public class Block7CrudValidation2Application {

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidation2Application.class, args);
	}


}
