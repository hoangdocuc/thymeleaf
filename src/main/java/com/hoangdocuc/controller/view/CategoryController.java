package com.hoangdocuc.controller.view;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    // show categories
    @GetMapping(value = "/category")
    public ModelAndView showList(@RequestParam(value = "message",required = false)String message){
        ModelAndView mav = new ModelAndView("/category/categoryList");
        List<CategoryDTO> categoryDTOS = iCategoryService.findAll();
        mav.addObject("categoryList",categoryDTOS);
        if(message!=null) {
            if (message.equals("insert_success")) {
                mav.addObject("message", "Thêm thành công");
            } else if (message.equals("update_success")) {
                mav.addObject("message", "Cập nhật thành công");
            } else if (message.equals("delete_success")) {
                mav.addObject("message", "Xóa thành công");
            }
        }
        return mav;
    }

    // go to crete or update category page
    @GetMapping(value = "/category/edit")
    public ModelAndView showCategory(@RequestParam(value = "id",required = false)Long id){
        ModelAndView mav = new ModelAndView("/category/categoryDetail");
        if(id!=null) {
            mav.addObject("categoryDetail", iCategoryService.findById(id));
        } else {
            mav.addObject("categoryDetail",new CategoryDTO());
        }
        return mav;
    }
    // create or update category
    @PostMapping(value = "/category/save")
    public String saveCategory(@ModelAttribute("categoryDetail") CategoryDTO categoryDTO){
        iCategoryService.save(categoryDTO);
        if(categoryDTO.getId()!=null){
            return "redirect:/category?message=update_success";
        } else {
            return "redirect:/category?message=insert_success";
        }
    }
    // delete category
    @GetMapping(value = "/category/delete")
    public String deleteCategory(@RequestParam("id")Long id){
        iCategoryService.delete(id);
        return "redirect:/category?message=delete_success";
    }
    //search category
    @GetMapping(value = "/searchCategory")
    public ModelAndView searchCategory(@RequestParam("categoryName") String categoryName){
        ModelAndView mav = new ModelAndView("/category/categoryList");
        List<CategoryDTO> categoryDTOS = iCategoryService.findByName(categoryName);
        mav.addObject("categoryList",categoryDTOS);
        return mav;
    }

}
