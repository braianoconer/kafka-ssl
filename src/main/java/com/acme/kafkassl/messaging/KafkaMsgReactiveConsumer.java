package com.acme.kafkassl.messaging;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.kafka.receiver.KafkaReceiver;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class KafkaMsgReactiveConsumer {

	private final KafkaReceiver<String, String> kafkaReceiver;
	private ConnectableFlux<String> connectableFlux;

	@PostConstruct
	public void init() {

		connectableFlux = kafkaReceiver.receive()
				.map(consumerRecord -> {

					log.info("received from kafka _key: {}", consumerRecord.key());
					log.debug("received from kafka _val: {}", consumerRecord.value());

					return consumerRecord.value();
				})
				.publish();

		connectableFlux.connect();
	}

}