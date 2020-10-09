package com.hoangdocuc.controller.api;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryControllerAPI {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping(value = "/api/category")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        return iCategoryService.save(categoryDTO);
    }

    @PutMapping(value = "/api/category/{id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO,
                                      @PathVariable("id")Long id){
        categoryDTO.setId(id);
        return iCategoryService.save(categoryDTO);
    }

    @DeleteMapping(value = "/api/category")
    public void deleteCategory(@RequestBody long id){
        iCategoryService.delete(id);
    }


}
