package com.ejercicio3.block5logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@Slf4j
public class Block5LoggingApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Block5LoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Block5LoggingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.error("Esto es un mensaje de error");
		logger.warn("Esto es un mensaje de advertencia");
		logger.info("Esto es un mensaje de información");
	}
}
