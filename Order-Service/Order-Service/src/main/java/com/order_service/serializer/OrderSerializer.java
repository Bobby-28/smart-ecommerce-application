package com.order_service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order_service.entities.Order;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class OrderSerializer implements Serializer<Order> {

    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void configure(Map configs, boolean isKey) {
    }


    @Override
    public byte[] serialize(String s, Order order) {
        byte[] val= null;
        try{
            val= objectMapper.writeValueAsString(order).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void close() {
    }
}
