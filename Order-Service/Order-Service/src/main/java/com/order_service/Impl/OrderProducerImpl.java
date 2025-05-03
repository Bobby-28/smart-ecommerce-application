package com.order_service.Impl;

import com.order_service.entities.Order;
import com.order_service.service.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerImpl implements OrderProducer {

    KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    OrderProducerImpl(KafkaTemplate<String, Order> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }
    @Override
    public void sendData(Order order) {
        Message<Order> message= MessageBuilder.withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, "order-data-topic-ecommerce").build();
        kafkaTemplate.send(message);
    }
}
