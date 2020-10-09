package com.hoangdocuc.converter;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCode(categoryDTO.getCode());
        return categoryEntity;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO,CategoryEntity categoryEntity){
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCode(categoryDTO.getCode());
        return categoryEntity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setCode(categoryEntity.getCode());
        categoryDTO.setCreatedDate(categoryEntity.getCreatedDate());
        categoryDTO.setCreatedBy(categoryEntity.getCreatedBy());
        categoryDTO.setModifiedDate(categoryEntity.getModifiedDate());
        categoryDTO.setModifiedBy(categoryEntity.getModifiedBy());
        return categoryDTO;
    }


}
