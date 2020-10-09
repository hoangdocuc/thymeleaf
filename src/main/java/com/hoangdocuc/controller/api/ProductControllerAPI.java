package com.hoangdocuc.controller.api;

import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductControllerAPI {

    @Autowired
    private IProductService iProductService;

    @PostMapping(value = "/api/product")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return iProductService.save(productDTO);
    }

    @PutMapping(value = "/api/product/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO,
                                    @PathVariable("id")Long id){
        productDTO.setId(id);
        return iProductService.save(productDTO);
    }
    @DeleteMapping(value = "/api/product")
    public void deleteProduct(@RequestBody long id){
        iProductService.delete(id);
    }



}
