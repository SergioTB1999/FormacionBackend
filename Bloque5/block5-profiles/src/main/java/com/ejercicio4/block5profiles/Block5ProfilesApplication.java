package com.ejercicio4.block5profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}

	@Value("${bd.url}")
	private String bdUrl;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Perfil activo " + System.getProperty("spring.profiles.active"));
		System.out.println("bd.url: " + bdUrl);
	}
}
