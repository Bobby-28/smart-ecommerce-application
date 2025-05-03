package com.order_consumer.service;

import com.order_consumer.entities.Order;
import com.order_consumer.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @Autowired
    OrderRepository orderRepository;

    @KafkaListener(topics = "order-data-topic-ecommerce", groupId = "order-consumer-group")
    public void listen(Order order){
        try {
            orderRepository.save(order);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
