package com.acme.kafkassl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class SslConfig {

    @Value("${javax.net.ssl.trust-store}")
    private String trustStore;

    @Value("${javax.net.ssl.trust-store-password}")
    private String trustStorePassword;

    @PostConstruct
    public void init() {
        System.setProperty("javax.net.ssl.trustStore", trustStore);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    }

}
