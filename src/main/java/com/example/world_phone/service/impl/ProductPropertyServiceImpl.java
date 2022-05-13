package com.example.world_phone.service.impl;


import com.example.world_phone.dto.respone.product.ProductPropertyRespone;
import com.example.world_phone.entity.ProductPropertyEntity;
import com.example.world_phone.repo.PropertyProductRepo;
import com.example.world_phone.service.IProductPropertyService;
import com.example.world_phone.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPropertyServiceImpl implements IProductPropertyService {
    private final PropertyProductRepo repo;
    private final ConvertUtil convertUtil;

    @Override
    public List<ProductPropertyRespone> findByRom(Long id) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductPropertyEntity> entityList = repo.findByRom(id);
        for (ProductPropertyEntity a: entityList
             ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }



    private ProductPropertyRespone mapToDto(ProductPropertyEntity entity){
        if(entity == null){
            return null;
        }else {
            ProductPropertyRespone dto = new ProductPropertyRespone();
            dto.setColor(entity.getColor());
            dto.setPrice(entity.getPrice());
            dto.setQuantity(entity.getQuantity());
            dto.setPriceString(convertUtil.moneyToStringFormat(entity.getPrice()));
            return dto;
        }
    }
}
