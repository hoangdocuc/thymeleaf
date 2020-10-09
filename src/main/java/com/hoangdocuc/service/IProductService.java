package com.hoangdocuc.service;

import com.hoangdocuc.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    List<ProductDTO> findByName(String name);
    ProductDTO save(ProductDTO productDTO);
    void delete(long id);

}
