package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.AttributeRequestAdd;
import com.example.world_phone.dto.request.attribute.AttributeRequestEdit;
import com.example.world_phone.dto.respone.attribute.AttributeRespone;

public interface AttributeProductService {
    AttributeRespone findByProduct(Long id);

    String saveAttribute(AttributeRequestAdd requestAdd, Long productId) ;

    String updateAttribute(AttributeRequestEdit attributeRequestEdit, Long productId);
}
