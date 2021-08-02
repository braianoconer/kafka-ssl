package com.acme.kafkassl.controller;

import com.acme.kafkassl.messaging.KafkaMsgReactiveConsumer;
import com.acme.kafkassl.messaging.MsgPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class KafkaStreamController {

    private final MsgPublisher<String, String> msgPublisher;
    private final KafkaMsgReactiveConsumer kafkaService;

    @GetMapping(value = "/publish/{msg}")
    public String publish(@PathVariable String msg) {
        return msgPublisher.publish(msg);
    }

    @GetMapping(value = "/consume/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        return kafkaService.getConnectableFlux();
    }

}