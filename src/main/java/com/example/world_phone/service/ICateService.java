package com.example.world_phone.service;

import com.example.world_phone.dto.respone.category.CategoryDTO;
import com.example.world_phone.dto.respone.category.CategoryResponeDto;

import java.util.List;

public interface ICateService {
    List<CategoryResponeDto> findAll();

    String save(CategoryDTO request);

    String edit(CategoryDTO request);

    CategoryResponeDto findById(String id);

    String delete(Long id);
}
