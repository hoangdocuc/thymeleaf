package com.hoangdocuc.converter;

import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductEntity toEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setImage(productDTO.getImage());
        return productEntity;
    }

    public ProductEntity toEntity(ProductDTO productDTO,ProductEntity productEntity){
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setImage(productDTO.getImage());
        return productEntity;
    }

    public ProductDTO toDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setCategoryName(productEntity.getCategory().getName());
        productDTO.setCategoryCode(productEntity.getCategory().getCode());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setQuantity(productEntity.getQuantity());
        productDTO.setImage(productEntity.getImage());
        productDTO.setCreatedDate(productEntity.getCreatedDate());
        productDTO.setCreatedBy(productEntity.getCreatedBy());
        productDTO.setModifiedDate(productEntity.getModifiedDate());
        productDTO.setModifiedBy(productEntity.getModifiedBy());
        return productDTO;
    }

}
