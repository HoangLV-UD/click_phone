package com.example.world_phone.service;

import com.example.world_phone.dto.respone.attribute.AttributeRespone;

public interface IAttributeService {
    AttributeRespone findByProductId(Long id);
}
