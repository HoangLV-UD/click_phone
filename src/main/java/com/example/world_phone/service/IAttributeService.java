package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.AttributeRequestAdd;
import com.example.world_phone.dto.request.attribute.AttributeRequestEdit;
import com.example.world_phone.dto.respone.attribute.AttributeRespone;

public interface IAttributeService {
    AttributeRespone findByProductId(Long id);

    String createAttribute(AttributeRequestAdd requestAdd, Long idProduct);

    String updateAttribute(AttributeRequestEdit attributeRequestEdit);
}
