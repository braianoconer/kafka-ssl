package com.acme.kafkassl.messaging;

public interface MsgPublisher<K, V> {

    K publish(V msg);
}
