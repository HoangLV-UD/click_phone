package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.attribute.AttributeRequestAdd;
import com.example.world_phone.dto.request.attribute.AttributeRequestEdit;
import com.example.world_phone.dto.respone.attribute.AttributeRespone;
import com.example.world_phone.entity.AttributeEntity;
import com.example.world_phone.entity.ProductEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.AttributeRepo;
import com.example.world_phone.repo.ProductRepo;
import com.example.world_phone.service.IAttributeService;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements IAttributeService {

    private final AttributeRepo attributeRepo;
    private final SessionUtil sessionUtil;

    private final ProductRepo productRepo;


    @Override
    public AttributeRespone findByProductId(Long id) {
        List<AttributeEntity> list = attributeRepo.findByProductId(id);
        if(list.size() == 0){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_ATTRIBUTE);
        }
        return mapToDto(list.get(0));

    }

    @Override
    public String createAttribute(AttributeRequestAdd requestAdd, Long id) {
        AttributeEntity entity = mapToEntity(requestAdd);
        ProductEntity productEntity = productRepo.findByIdAndDeleteFlagIsFalse(id);
        if(productEntity == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST);
        }
        entity.setProductId(id);
        attributeRepo.save(entity);
        return "ok";
    }

    @Override
    public String updateAttribute(AttributeRequestEdit edit) {
        if(edit.getId() == null){
            throw  new WorldPhoneExp(ConstansErrorCode.ATTRIBUTE_NOT_EXIST);
        }
        AttributeEntity entity = attributeRepo.findByIdAndDeleteFlagIsFalse(edit.getId());
        if(entity == null){
            throw  new WorldPhoneExp(ConstansErrorCode.ATTRIBUTE_NOT_EXIST);
        }
        Long productId = entity.getProductId();
        entity = mapToEntityEdit(edit);
        entity.setProductId(productId);


        attributeRepo.save(entity);
        return "ok";
    }


    private AttributeRespone mapToDto(AttributeEntity a){
        if(a == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_ATTRIBUTE);
        }else {
            AttributeRespone b = new AttributeRespone();
            b.setId(String.valueOf(a.getId()));
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

    private AttributeEntity mapToEntity(AttributeRequestAdd x){
        if(x == null){
            throw new WorldPhoneExp(ConstansErrorCode.ATTRIBUTE_NOT_EXIST);
        }
        AttributeEntity entity = new AttributeEntity();
        entity.setCamSau(x.getCamSau());
        entity.setCamTruoc(x.getCamTruoc());
        entity.setChip(x.getChip());
        entity.setDeleteFlag(false);
        entity.setPin(x.getPin());
        entity.setRam(x.getRam());
        entity.setOperatingSystem(x.getOperatingSystem());
        entity.setScreen(x.getScreen());
        entity.setSim(x.getSim());
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        return entity;

    }

    private AttributeEntity mapToEntityEdit(AttributeRequestEdit x){
        if(x == null){
            throw new WorldPhoneExp(ConstansErrorCode.ATTRIBUTE_NOT_EXIST);
        }
        AttributeEntity entity = new AttributeEntity();
        entity.setId(x.getId());
        entity.setCamSau(x.getCamSau());
        entity.setCamTruoc(x.getCamTruoc());
        entity.setChip(x.getChip());
        entity.setDeleteFlag(false);
        entity.setPin(x.getPin());
        entity.setRam(x.getRam());
        entity.setOperatingSystem(x.getOperatingSystem());
        entity.setScreen(x.getScreen());
        entity.setSim(x.getSim());
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        return entity;

    }
}
