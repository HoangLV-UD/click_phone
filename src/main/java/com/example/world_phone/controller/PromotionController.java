package com.example.world_phone.controller;

import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.dto.respone.promotion.PromotionResponseDTO;
import com.example.world_phone.service.IProductService;
import com.example.world_phone.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 29/07/2022
 * Project_name: com.example.world_phone.controller
 */

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private IPromotionService service;

    @Autowired
    private IProductService productService;


    @GetMapping()
    public String getAll(Model model){
        List<PromotionResponseDTO> promotionList = service.getAll();

        model.addAttribute("promotions", promotionList);
//
//        List<CategoryResponse> lstCategory = categoryService.getAllCategory();
//        model.addAttribute("categories", lstCategory);
//
        List<ProductResponse> lstProduct = productService.findAll();
        model.addAttribute("products", lstProduct);
        return "views/promotion/promotion.html";
    }
}