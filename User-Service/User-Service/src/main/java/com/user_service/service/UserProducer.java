package com.user_service.service;

import com.user_service.entities.User;
import org.springframework.stereotype.Service;

public interface UserProducer {
    void sendData(User user);
}
