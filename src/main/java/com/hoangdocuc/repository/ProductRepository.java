package com.hoangdocuc.repository;

import com.hoangdocuc.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByNameContaining(String name);
}
