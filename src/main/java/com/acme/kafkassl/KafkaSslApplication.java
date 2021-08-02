package com.acme.kafkassl;

import com.acme.kafkassl.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@SpringBootApplication
@EnableConfigurationProperties({ServiceConfig.class})
public class KafkaSslApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSslApplication.class, args);
	}

}
