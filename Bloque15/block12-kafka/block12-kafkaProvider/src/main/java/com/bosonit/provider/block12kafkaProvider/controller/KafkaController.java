package com.bosonit.provider.block12kafkaProvider.controller;

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

public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @PostMapping()
    public ResponseEntity<String> enviaMensaje(@RequestBody String mensaje){
        kafkaTemplate.send("prueba2", mensaje);
        return ResponseEntity.ok().body("Mensaje enviado");
    }

    @KafkaListener(topics = {"prueba1"}, groupId = "my-group-id")
    public void listener(String message){
        System.out.println("Mensaje recibido: " + message);
    }

}
