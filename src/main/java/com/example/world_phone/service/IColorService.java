package com.example.world_phone.service;


import com.example.world_phone.dto.request.attribute.color.ColorRequest;
import com.example.world_phone.dto.respone.color.ColorRespone;

import java.util.List;

public interface IColorService {
    public List<ColorRespone> findAll();
    ColorRespone findById(String id);

    String createColor(ColorRequest request);

    String updateColor(ColorRequest request);

    String deleteChip(Long id);

}
