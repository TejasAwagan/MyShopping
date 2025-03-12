package com.devtejas.myshopping.repository;

import com.devtejas.myshopping.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    static void deleteAllByCartId(Long id) {
    }
}
