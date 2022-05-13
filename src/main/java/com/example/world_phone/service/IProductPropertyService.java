package com.example.world_phone.service;

import com.example.world_phone.dto.respone.product.ProductPropertyRespone;

import java.util.List;

public interface IProductPropertyService {
    List<ProductPropertyRespone> findByRom(Long id);
}
