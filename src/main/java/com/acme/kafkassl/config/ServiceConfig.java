package com.acme.kafkassl.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@Getter
@Setter
@ConfigurationProperties("service")
public class ServiceConfig {

    private String kafkaTopicName;

    @Bean
    NewTopic createTopic() {
        return new NewTopic(kafkaTopicName, 1, (short) 1);
    }

}
