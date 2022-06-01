package com.example.world_phone.service.impl;

import com.example.world_phone.dto.respone.color.ColorRespone;
import com.example.world_phone.entity.ColorEntity;
import com.example.world_phone.repo.ColorRepo;
import com.example.world_phone.service.IColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements IColorService {

    private final ColorRepo colorRepo;

    @Override
    public List<ColorRespone> findAll() {
        List<ColorEntity> entityList = colorRepo.findByDeleteFlagIsFalse();
        List<ColorRespone> colorRespones = new ArrayList<>();
        for (ColorEntity e: entityList
             ) {
            colorRespones.add(mapToRespone(e));
        }
        return colorRespones;
    }


    private ColorRespone mapToRespone(ColorEntity entity){
        return new ColorRespone(entity.getId(), entity.getValueColor());
    }
}
