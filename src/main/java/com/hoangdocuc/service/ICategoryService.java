package com.hoangdocuc.service;

import com.hoangdocuc.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    List<CategoryDTO> findByName(String name);
    CategoryDTO save(CategoryDTO categoryDTO);
    void delete(long id);
    CategoryDTO findByCode(String code);
}
