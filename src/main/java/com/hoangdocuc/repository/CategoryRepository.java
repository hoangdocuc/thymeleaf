package com.hoangdocuc.repository;

import com.hoangdocuc.entity.CategoryEntity;
import com.hoangdocuc.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findCategoryEntityByCode(String code);
    List<CategoryEntity> findByNameContaining(String name);
}
