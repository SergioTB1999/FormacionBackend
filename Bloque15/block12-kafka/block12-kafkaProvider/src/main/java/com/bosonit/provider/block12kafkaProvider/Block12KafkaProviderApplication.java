package com.bosonit.provider.block12kafkaProvider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Block12KafkaProviderApplication {


	public static void main(String[] args) {
		SpringApplication.run(Block12KafkaProviderApplication.class, args);
	}



	@Bean
	CommandLineRunner init(KafkaTemplate<String,String> kafkaTemplate){
		return args -> {
			kafkaTemplate.send("topicKafkaBloque12", "Mensaje de arrancado");
		};
	}

}
