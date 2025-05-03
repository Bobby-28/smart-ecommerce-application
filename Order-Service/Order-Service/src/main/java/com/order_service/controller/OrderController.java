package com.order_service.controller;

import com.order_service.dto.CartItemDto;
import com.order_service.entities.Order;
import com.order_service.service.JwtService;
import com.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    JwtService jwtService;
    @PostMapping("/create")
    Mono<ResponseEntity<?>> create(@RequestBody CartItemDto cartItemDto, @RequestHeader("Authorization") String authHeader){
        if(extractUserId(authHeader)!=null){
            return extractUserId(authHeader)
                    .flatMap(userId -> {
                        Order order= new Order();
                        order.setUserId(userId);
                        order.setItems(cartItemDto.getItems());
                        order.setShippingAddress(cartItemDto.getShippingAddress());
                        return orderService.create(order)
                                .map(saveOrder -> ResponseEntity.status(HttpStatus.CREATED).body(saveOrder));
                    });
        }else{
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body("The Token is expired aur UnAuthorized"));
        }
    }

    @GetMapping("/getAll")
    public Mono<ResponseEntity<?>> getByUserId(@RequestHeader("Authorization") String authHeader) {
        if(extractUserId(authHeader)!=null){
            return extractUserId(authHeader)
                    .flatMap(userId -> orderService.getAll(userId)
                            .collectList()
                            .map(orders -> ResponseEntity.ok(orders))
                    );
        }else{
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body("The Token is expired aur UnAuthorized"));
        }
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<?>> getById(@PathVariable String id, @RequestHeader("Authorization") String authHeader) {
        if(extractUserId(authHeader)!=null){
            return extractUserId(authHeader)
                    .flatMap(userId -> orderService.get(id)
                            .map(order -> ResponseEntity.ok(order))
                    );
        }else{
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body("The Token is expired aur UnAuthorized"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<?>> delete(@PathVariable String id, @RequestHeader("Authorization") String authHeader) {
        if(extractUserId(authHeader)!=null){
            return extractUserId(authHeader)
                    .flatMap(userId -> orderService.delete(id)
                            .thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build())
                    );
        }else{
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body("The Token is expired aur UnAuthorized"));
        }
    }
    Mono<String> extractUserId(String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            return Mono.just(jwtService.extractUserId(token));
        }else{
            return Mono.empty();
        }
    }
}
