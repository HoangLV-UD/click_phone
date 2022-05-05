package com.example.world_phone.api;


import com.example.world_phone.dto.request.category.CategoryReqDto;
import com.example.world_phone.dto.respone.category.CategoryDTO;
import com.example.world_phone.entity.CategoryEntity;
import com.example.world_phone.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryApi {

    private final ICategoryService categoryService;


    @PostMapping("")
    public String saveCategory(@RequestBody CategoryReqDto categoryDto){
        System.out.println(categoryDto.toString());
        categoryService.saveCategory(categoryDto);
        return "";
    }

    @PutMapping("")
    public String updateCategory(@RequestBody CategoryDTO categoryDto){
        System.out.println(categoryDto.toString());
        categoryService.updateCategory(categoryDto);
        return "";
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getByCategoryId(@PathVariable("categoryId") String categoryId){
        CategoryEntity categoryEntity = categoryService.findById(categoryId);
        CategoryDTO categoryDto = CategoryDTO.builder()
                .categoryId(categoryId)
                .categoryName(categoryEntity.getName())
                .build();
        return categoryDto;
    }

    @DeleteMapping("/{categoryId}")
    public void deleteByCategoryId(@PathVariable("categoryId") String categoryId){
        CategoryEntity categoryEntity = categoryService.findById(categoryId);
        categoryEntity.setDeleteFlag(true);
        categoryService.deleteCategory(categoryEntity);
    }

}
