package com.devtejas.myshopping.repository;

import com.devtejas.myshopping.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image , Long> {
    List<Image> findByProductId(Long id);
}
