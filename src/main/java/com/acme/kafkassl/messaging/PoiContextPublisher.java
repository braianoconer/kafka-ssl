package com.acme.kafkassl.messaging;


import com.acme.kafkassl.config.ServiceConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoiContextPublisher implements MsgPublisher<String, String> {

    @NonNull
    private final ServiceConfig config;

    @NonNull
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public String publish(String msg) {

        log.debug("to kafka [{}]", msg);

        String key = String.valueOf(Instant.now().toEpochMilli());
        kafkaTemplate.send(config.getKafkaTopicName(), key, msg);

        return key;
    }

}
