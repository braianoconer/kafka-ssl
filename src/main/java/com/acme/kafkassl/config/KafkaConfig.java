package com.acme.kafkassl.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.internals.ConsumerFactory;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String registryUrl;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

//    @Value("${spring.kafka.jaas.enabled}")
//    private Boolean isJaasEnabled;
//
//    @Value("${spring.kafka.properties.security.protocol}")
//    private String securityProtocol;
//
//    @Value("${spring.kafka.properties.sasl.mechanism}")
//    private String saslMechanism;
//
//    @Value("${spring.kafka.properties.sasl.jaas.config}")
//    private String saslJaasConfig;

    @Value("${service.kafka-topic-name}")
    private String kafkaTopicName;


    @Bean
    KafkaReceiver<String, String> kafkaReceiver() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        configProps.put("schema.registry.url", registryUrl);

//        configProps.put("jaas.enabled", isJaasEnabled);
//        configProps.put("security.protocol", securityProtocol);
//        configProps.put("sasl.mechanism", saslMechanism);
//        configProps.put("sasl.jaas.config", saslJaasConfig);

        return new DefaultKafkaReceiver<>(ConsumerFactory.INSTANCE,
                ReceiverOptions.<String, String>create(configProps)
                        .subscription(Collections.singleton(kafkaTopicName))
        );
    }

}
