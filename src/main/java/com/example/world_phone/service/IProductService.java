package com.example.world_phone.service;

import com.example.world_phone.dto.request.product.ProductRequestAdd;
import com.example.world_phone.dto.request.product.ProductRequestEdit;
import com.example.world_phone.dto.respone.product.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> findAll();

    String createProduct(ProductRequestAdd requestProduct);

    String editProduct(ProductRequestEdit requestEdit);

    ProductResponse getProduct(Long id);

    String deleteProduct(Long id);

    String editStatusProduct(Long id, String value);


}
