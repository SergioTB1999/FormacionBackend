package com.bosonit.provider.block12kafkaProvider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String,String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE); // DELETE BORRARA EL MENSAJE, COMPACT MANTIENE EL MAS ACTUAL
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"8640000"); //TIEMPO QUE VA A DURAR EL MENSAJE EN EL SERVIDOR
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824"); // TAMAÑO MAXIMO DEL SEGMENTO - 1GB EN BYTES
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); //TAMAÑO EN BYTES DEL MENSAJE - POR DEFECTO 1 MB

        return TopicBuilder.name("topicKafkaBloque12")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }

}
