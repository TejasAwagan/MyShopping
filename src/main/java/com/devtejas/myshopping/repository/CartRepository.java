package com.devtejas.myshopping.repository;

import com.devtejas.myshopping.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
