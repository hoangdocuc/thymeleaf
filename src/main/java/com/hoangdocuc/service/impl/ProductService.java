package com.hoangdocuc.service.impl;

import com.hoangdocuc.converter.ProductConverter;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.entity.CategoryEntity;
import com.hoangdocuc.entity.ProductEntity;
import com.hoangdocuc.repository.CategoryRepository;
import com.hoangdocuc.repository.ProductRepository;
import com.hoangdocuc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity entity : productEntities) {
            ProductDTO productDTO = productConverter.toDTO(entity);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity productEntity = productRepository.findOne(id);
        return productConverter.toDTO(productEntity);
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findByNameContaining(name);
        for (ProductEntity productEntity: productEntities) {
            ProductDTO productDTO  = productConverter.toDTO(productEntity);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCode(productDTO.getCategoryCode());
        ProductEntity productEntity = new ProductEntity();
        if(productDTO.getId()==null){
            productEntity = productConverter.toEntity(productDTO);
            productEntity.setCategory(categoryEntity);
        }else {
            ProductEntity oldProduct = productRepository.findOne(productDTO.getId());
            oldProduct.setCategory(categoryEntity);
            productEntity = productConverter.toEntity(productDTO,oldProduct);
        }
        return productConverter.toDTO(productRepository.save(productEntity));
    }

    @Override
    @Transactional
    public void delete(long id) {
        productRepository.delete(id);
    }
}
