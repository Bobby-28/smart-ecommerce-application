package com.order_consumer.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order_consumer.entities.Order;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class OrderDeserializer implements Deserializer<Order> {

    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Order deserialize(String s, byte[] bytes) {
        Order order= null;
        try {
            order= objectMapper.readValue(bytes, Order.class);
        }catch (Exception e){
            throw new RuntimeException("❌ Failed to deserialize message", e);
        }
        return order;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
