package com.user_service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_service.entities.User;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User> {
    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, User user) {
        byte[] val= null;
        try{
            val= objectMapper.writeValueAsString(user).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void close() {
    }
}
