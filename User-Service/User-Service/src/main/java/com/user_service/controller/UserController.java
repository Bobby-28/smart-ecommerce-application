package com.user_service.controller;

import com.user_service.entities.Token;
import com.user_service.entities.User;
import com.user_service.request.LoginRequest;
import com.user_service.request.UserRequest;
import com.user_service.service.JwtService;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping("/get")
    ResponseEntity<?> get(@RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            String userId= jwtService.extractUserId(token);
            return ResponseEntity.status(HttpStatus.OK).body(userService.get(userId));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Required");
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody UserRequest userRequest, @RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            String userId= jwtService.extractUserId(token);
            User user= userService.get(userId);
            if(userRequest.getPassword()!=null){
                user.setPassword(userRequest.getPassword());
            }if(userRequest.getPhone()!=null){
                user.setPhone(userRequest.getPhone());
            }if(userRequest.getAddress()!=null){
                user.setAddress(userRequest.getAddress());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(user));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Required");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest){
        User user= userService.getByEmail(loginRequest.getEmail());
        if(Objects.equals(user.getPassword(), loginRequest.getPassword())){
            Token token= userService.getToken(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Authorization", "Bearer " + token.getAccessToken())
                    .header("Authorization", "Bearer " + token.getRefreshToken())
                    .body(token);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is a problem !!!!!");
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> delete(@RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            String userId= jwtService.extractUserId(token);
            userService.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body("User Data has been deleted" + userService.get(userId));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Required");
        }
    }
}
