package com.example.world_phone.api;


import com.example.world_phone.dto.request.attribute.ram.RamRequest;
import com.example.world_phone.dto.request.category.CategoryReqDto;
import com.example.world_phone.dto.respone.category.CategoryDTO;
import com.example.world_phone.entity.CategoryEntity;
import com.example.world_phone.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryApi {

    private final ICategoryService categoryService;


    @PostMapping
    public String saveCategory(@RequestBody CategoryReqDto categoryDto){
        System.out.println(categoryDto.toString());
        categoryService.saveCategory(categoryDto);
        return "";
    }

    @PutMapping
    public String updateCategory(@RequestBody CategoryDTO categoryDto){
        System.out.println(categoryDto.toString());
        categoryService.updateCategory(categoryDto);
        return "";
    }
//@PutMapping
//public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO request) {
//    String status = categoryService.updateCategory(request);
//    if (status.equalsIgnoreCase("ok")) {
//        return ResponseEntity.ok().body(request);
//    }
//    return ResponseEntity.badRequest().body(request);
//}


    @GetMapping("/{categoryId}")
    public CategoryDTO getByCategoryId(@PathVariable("categoryId") String categoryId){
        CategoryEntity categoryEntity = categoryService.findById(categoryId);
        CategoryDTO categoryDto = CategoryDTO.builder()
                .id(categoryId)
                .name(categoryEntity.getName())
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
