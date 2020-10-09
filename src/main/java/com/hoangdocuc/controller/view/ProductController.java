package com.hoangdocuc.controller.view;

import com.hoangdocuc.dto.ImageDTO;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;

    // show products
    @GetMapping(value = {"/product", "/"})
    public ModelAndView showList(@RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView("/product/productList");
        mav.addObject("productList", iProductService.findAll());
        if (message != null) {
            if (message.equals("insert_success")) {
                mav.addObject("message", "Thêm thành công");
            } else if (message.equals("update_success")) {
                mav.addObject("message", "Cập nhật thành công");
            } else if (message.equals("delete_success")) {
                mav.addObject("message", "Xóa thành công");
            } else if(message.equals("sendemail_success")){
                mav.addObject("message","Gửi thành công, kiểm tra mail");
            }
        }
        return mav;
    }
    // go to create or update page
    @GetMapping(value = "/product/edit")
    public ModelAndView showProduct(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("/product/productDetail");
        if (id != null) {
            mav.addObject("productDetail", iProductService.findById(id));
        } else {
            mav.addObject("productDetail", new ProductDTO());
        }
        mav.addObject("categories", iCategoryService.findAll());
        return mav;
    }

    // delete product
    @GetMapping(value = "/product/delete")
    public String deleteProduct(@RequestParam(value = "id") Long id) {
        iProductService.delete(id);
        return "redirect:/product?message=delete_success";
    }

    // create or update product
    @PostMapping(value = "/product/save")
    public String saveProduct(@ModelAttribute("productDetail") ProductDTO productDTO,
                              @RequestParam(value = "photo") MultipartFile photo) throws IOException {
        productDTO.setImage(photo.getOriginalFilename().toLowerCase());
        iProductService.save(productDTO);
        saveImage(photo);
        if (productDTO.getId() != null) {
            return "redirect:/product?message=update_success";
        } else {
            return "redirect:/product?message=insert_success";
        }
    }
    // method save file in folder : uploads
    public void saveImage(MultipartFile image) {
        Path path = Paths.get("uploads/");
        try {
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, path.resolve(image.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //test upload file
    @PostMapping(value = "/image/save")
    public String save(@RequestParam("image") MultipartFile image, Model model){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage(image.getOriginalFilename());
        if (image.isEmpty()) {
            return "product/productList";
        }
        saveImage(image);
        imageDTO.setImage(image.getOriginalFilename().toLowerCase());
        model.addAttribute("image", imageDTO.getImage());
        return "/product/anh";
    }
    //go to image upload page
    @GetMapping(value = "/image")
    public ModelAndView view() {
        ModelAndView mav = new ModelAndView("product/upload");
        return mav;
    }
    // search product
    @GetMapping(value = "/searchProduct")
    public ModelAndView searchProduct(@RequestParam("productName") String productName){
        ModelAndView mav = new ModelAndView("product/productList");
        List<ProductDTO> productDTOS = iProductService.findByName(productName);
        mav.addObject("productList",productDTOS);
        return mav;
    }



}
