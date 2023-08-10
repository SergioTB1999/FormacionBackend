package com.ejercicio1Bloque5.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block5CommandLineRunnerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception { //Esto se mostrara justo despues de arrancar la SpringBootApplication
		funcion3("Soy la tercera clase");
	}

	@PostConstruct //Este mensaje se mostrara el 1º ya que se ejecuta antes de arrancar la SpringBootApplication
	public void funcion1(){
		System.out.println("Hola desde el inicio de clase");
	}

	@Bean //Este mensaje se mostrara el ultimo
	CommandLineRunner funcion2(){
		return message -> {
			System.out.println("Hola desde clase secundaria");
		};
	}

	public void funcion3(String frase){
		System.out.println(frase);
	}
}
