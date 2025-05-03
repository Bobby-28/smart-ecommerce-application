package com.order_service.service;

import com.order_service.entities.Order;
import org.springframework.stereotype.Service;


public interface OrderProducer {
    void sendData(Order order);
}
