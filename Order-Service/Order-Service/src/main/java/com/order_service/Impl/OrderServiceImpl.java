package com.order_service.Impl;

import com.order_service.dto.CartItemDto;
import com.order_service.entities.Order;
import com.order_service.repository.OrderRepository;
import com.order_service.service.OrderProducer;
import com.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProducer orderProducer;

    @Override
    public Mono<Order> create(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now().toString());
        order.setUpdatedAt(Instant.now().toString());
        return orderRepository.save(order)
                .doOnSuccess(savedOrder -> orderProducer.sendData(savedOrder));
    }

    @Override
    public Mono<Order> get(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Flux<Order> getAll(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Mono<Void> delete(String id) {
        return orderRepository.findById(id)
                .flatMap(order -> orderRepository.delete(order))
                .switchIfEmpty(Mono.error(new RuntimeException("Order Not Found")));
    }
}
