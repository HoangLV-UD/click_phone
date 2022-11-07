package com.example.world_phone.service;

import com.example.world_phone.dto.request.category.CategoryReqDto;
import com.example.world_phone.dto.respone.category.CategoryDTO;
import com.example.world_phone.dto.respone.category.CategoryResponeDto;
import com.example.world_phone.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponeDto> getAllCategory();
    CategoryEntity findById(String id);
    void saveCategory(CategoryReqDto categoryDto);
    String updateCategory(CategoryDTO categoryDto);
    void deleteCategory(CategoryEntity categoryEntity);
}
