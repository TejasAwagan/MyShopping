package com.devtejas.myshopping.controller;

import com.devtejas.myshopping.dto.OrderDto;
import com.devtejas.myshopping.exception.ResourceNotFoundException;
import com.devtejas.myshopping.models.Order;
import com.devtejas.myshopping.response.ApiResponse;
import com.devtejas.myshopping.service.order.IOrderService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix/orders}")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId){
        try{
            Order order = orderService.placeOrder(userId);
            return ResponseEntity.ok(new ApiResponse("Item order Success", order));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/{orderId}/order")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId){
        try{
            OrderDto order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("order Found Success",order));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId){
        try {
            List<OrderDto> order = orderService.getUserOrders(userId);
            return ResponseEntity.ok(new ApiResponse("Order found success", order));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}




















