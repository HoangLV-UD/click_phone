package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.respone.attribute.AttributeRespone;
import com.example.world_phone.entity.AttributeEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.AttributeRepo;
import com.example.world_phone.service.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements IAttributeService {

    private final AttributeRepo attributeRepo;


    @Override
    public AttributeRespone findByProductId(Long id) {
        List<AttributeEntity> list = attributeRepo.findByProductId(id);
        if(list.size() == 0){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_ATTRIBUTE);
        }
        return mapToDto(list.get(0));

    }


    private AttributeRespone mapToDto(AttributeEntity a){
        if(a == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_ATTRIBUTE);
        }else {
            AttributeRespone b = new AttributeRespone();
            b.setChip(a.getChip());
            b.setCamSau(a.getCamSau());
            b.setCamTruoc(a.getCamTruoc());
            b.setPin(a.getPin());
            b.setScreen(a.getScreen());
            b.setRam(a.getRam());
            b.setSim(a.getSim());
            b.setOperatingSystem(a.getOperatingSystem());
            return b;
        }
    }
}
