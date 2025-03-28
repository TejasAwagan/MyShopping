package com.devtejas.myshopping.service.cart;

import com.devtejas.myshopping.exception.ResourceNotFoundException;
import com.devtejas.myshopping.models.Cart;
import com.devtejas.myshopping.models.CartItem;
import com.devtejas.myshopping.repository.CartItemRepository;
import com.devtejas.myshopping.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;



@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart Not Found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        CartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getItems().stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        newCart = cartRepository.save(newCart);
        System.out.println("New cart initialized with ID: {}"+ newCart.getId());
        return newCart.getId();

    }


    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
