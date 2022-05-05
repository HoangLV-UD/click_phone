package com.example.world_phone.controller;


import com.example.world_phone.dto.respone.category.CategoryResponeDto;
import com.example.world_phone.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {


    private final ICategoryService categoryService;

    @GetMapping
    public String index(Model model){
        List<CategoryResponeDto> list = categoryService.getAllCategory();
        model.addAttribute("cate", list);
        return "views/category/category";
    }
}
