package com.order_service.service;

import com.order_service.dto.CartItemDto;
import com.order_service.entities.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface OrderService {
    Mono<Order> create(Order order);
    Mono<Order> get(String id);

    Flux<Order> getAll(String userId);
    Mono<Void> delete(String id);
}
