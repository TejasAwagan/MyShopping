package com.devtejas.myshopping.service.cart;

import com.devtejas.myshopping.models.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);


    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
