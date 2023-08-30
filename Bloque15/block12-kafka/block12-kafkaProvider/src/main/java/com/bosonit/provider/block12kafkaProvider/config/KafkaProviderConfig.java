package com.bosonit.provider.block12kafkaProvider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {

    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServer;

     public Map<String, Object> producerConfig(){
         Map<String,Object> properties = new HashMap<>();
         properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
         properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
         properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
         return properties;
     }


     public ProducerFactory<String,String> providerFactory(){
         return new DefaultKafkaProducerFactory<>(producerConfig());
     }


     public KafkaTemplate<String,String> kafkaTemplate(ProducerFactory<String,String> producerFactory){
         return new KafkaTemplate<>(producerFactory);
     }
}
