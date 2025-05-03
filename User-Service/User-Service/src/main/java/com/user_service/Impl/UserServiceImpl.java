package com.user_service.Impl;

import com.user_service.entities.Token;
import com.user_service.entities.User;
import com.user_service.respository.UserRepository;
import com.user_service.service.UserProducer;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProducer userProducer;
    @Override
    public User create(User user) {
        User existingUser= userRepository.findByEmail(user.getEmail());
        if(existingUser!=null){
            throw new RuntimeException("The Email Already Exist");
        }
        user.setUser_id(UUID.randomUUID().toString());
        userProducer.sendData(user);
        return user;
    }
    @Override
    public User get(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("The User Id Not Found"));
    }
    @Override
    public User update(User user) {
        userProducer.sendData(user);
        return user;
    }
    @Override
    public void delete(String email) {
        User user= userRepository.findByEmail(email);
        if(user==null){
            throw new RuntimeException("The User Not Found");
        }
        userRepository.delete(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Token getToken(User user) {
        Token token= restTemplate.postForObject("http://localhost:6062/api/v1/users/login", user, Token.class);
        return token;
    }
}
