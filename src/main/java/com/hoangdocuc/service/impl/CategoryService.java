package com.hoangdocuc.service.impl;

import com.hoangdocuc.converter.CategoryConverter;
import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.entity.CategoryEntity;
import com.hoangdocuc.repository.CategoryRepository;
import com.hoangdocuc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryDTO categoryDTO = categoryConverter.toDTO(categoryEntity);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findOne(id);
        return categoryConverter.toDTO(categoryEntity);
    }

    @Override
    public List<CategoryDTO> findByName(String name) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findByNameContaining(name);
        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryDTO categoryDTO = categoryConverter.toDTO(categoryEntity);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        if(categoryDTO.getId()==null){
            categoryEntity = categoryConverter.toEntity(categoryDTO);
        }else {
            CategoryEntity oldCategory = categoryRepository.findOne(categoryDTO.getId());
            categoryEntity = categoryConverter.toEntity(categoryDTO,oldCategory);
        }
        categoryRepository.save(categoryEntity);
        return categoryConverter.toDTO(categoryEntity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public CategoryDTO findByCode(String code) {
        CategoryEntity categoryEntity= categoryRepository.findCategoryEntityByCode(code);
        return categoryConverter.toDTO(categoryEntity);
    }
}
