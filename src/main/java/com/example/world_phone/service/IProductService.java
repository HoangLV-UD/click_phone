package com.example.world_phone.service;

import com.example.world_phone.dto.respone.product.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> findAll();
}
