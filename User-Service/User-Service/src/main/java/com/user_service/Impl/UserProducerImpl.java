package com.user_service.Impl;

import com.user_service.entities.User;
import com.user_service.service.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserProducerImpl implements UserProducer {

    KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    UserProducerImpl(KafkaTemplate<String, User> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }
    @Override
    public void sendData(User user) {
        Message<User> message= MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "user-data-topic-ecommerce").build();
        kafkaTemplate.send(message);
    }
}
