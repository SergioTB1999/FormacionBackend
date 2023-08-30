package com.bosonit.consumer.block12kafkaConsumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")

public class Controller {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping()
    public ResponseEntity<String> enviaMensaje(@RequestBody String mensaje){
        kafkaTemplate.send("prueba1", mensaje);
        return ResponseEntity.ok().body("Mensaje enviado");
    }

    @KafkaListener(topics = {"prueba2"}, groupId = "my-group-id")
    public void listener(String message){
        System.out.println("Mensaje recibido: " + message);
    }
}
