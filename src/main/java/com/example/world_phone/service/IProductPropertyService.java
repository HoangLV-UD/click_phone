package com.example.world_phone.service;

import com.example.world_phone.dto.request.product_property.ProductPropertyRequest;
import com.example.world_phone.dto.respone.order_detail.OrderDetailRespone;
import com.example.world_phone.dto.respone.product.ProductPropertyRespone;

import java.util.List;

public interface IProductPropertyService {
    List<ProductPropertyRespone> findByRom(Long id);

    OrderDetailRespone findById(Long id);

    List<ProductPropertyRespone> findByRomAndColor(String romId, String colorId);

    List<ProductPropertyRespone> findByColor(String colorId);

    ProductPropertyRespone updateProductProperty(ProductPropertyRequest request);

    String udpateStatusProductProperty(ProductPropertyRequest request);


}
