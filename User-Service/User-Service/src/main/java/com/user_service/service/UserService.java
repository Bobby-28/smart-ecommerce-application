package com.user_service.service;

import com.user_service.entities.Token;
import com.user_service.entities.User;
import org.springframework.stereotype.Service;

public interface UserService{
    User create(User user);
    User get(String id);
    User update(User user);
    void delete(String id);
    User getByEmail(String email);
    Token getToken(User user);
}
