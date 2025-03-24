package com.devtejas.myshopping.controller;

import com.devtejas.myshopping.exception.ResourceNotFoundException;
import com.devtejas.myshopping.models.Cart;
import com.devtejas.myshopping.response.ApiResponse;
import com.devtejas.myshopping.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {

    @Autowired
    ICartService cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId){
        try {
            Cart cart = cartService.getCart(cartId);
            if(cart == null){
                throw new ResourceNotFoundException("Cart Not found");
            }
            return ResponseEntity.ok(new ApiResponse("Success", cart));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId){
        try{
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear cart success !", null));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId){
        try{
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


}

















































