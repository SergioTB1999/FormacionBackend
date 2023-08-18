package com.ejercicio2.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block5PropertiesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block5PropertiesApplication.class, args);
	}

	@Value("${greeting}")
	private String nombre1;

	@Value("${my.number}")
	private  String nombre2;

	@Value("${new.property:No tiene valor}")
	private String nombre3;

	@Value("${MYURL}")
	private String nombre4;

	@Value("${MYURL2:NO_tengo_valor}")
	private String nombre5;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("El valor de greeting es: " + nombre1);
		System.out.println("El valor de my.number es: " + nombre2);
		System.out.println("El valor de new.property es: " + nombre3);
		System.out.println("El valor de env.MYURL es: " + nombre4);
		System.out.println("El valor de env.MYURL2 es: " + nombre5);
	}
}
