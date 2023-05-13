package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.attribute.AttributeRequestAdd;
import com.example.world_phone.dto.request.attribute.AttributeRequestEdit;
import com.example.world_phone.dto.respone.attribute.AttributeRespone;
import com.example.world_phone.entity.AttributeProductEntity;
import com.example.world_phone.entity.RamEntity;
import com.example.world_phone.repo.*;
import com.example.world_phone.service.AttributeProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author: hieu
 * @since: 06/07/2022
 * Project_name: com.example.world_phone.service.impl
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AttributeProductServiceImpl implements AttributeProductService {
    private final AttributeProductRepo repo;
    private final RamRepo ramRepo;
    private final ChipRepo chipRepo;
    private final OsRepo osRepo;
    private final PinRepo pinRepo;
    private final ScreenRepo screenRepo;
    private final CamRepo camRepo;

    @Override
    public AttributeRespone findByProduct(Long id) {
        AttributeProductEntity entity = repo.findByProductId(id);
        if(entity == null){
            return null;
        }
        AttributeRespone respone = new AttributeRespone();
        respone.setRam(String.valueOf(entity.getRamID()));
        respone.setChip(String.valueOf(entity.getChipId()));
        respone.setCam(String.valueOf(entity.getCamId()));
        respone.setOperatingSystem(String.valueOf(entity.getOsId()));
        respone.setPin(String.valueOf(entity.getPinId()));
        respone.setScreen(String.valueOf(entity.getScreenId()));
        return respone;
    }

    @Override
    public String saveAttribute(AttributeRequestAdd requestAdd, Long productId) {
        AttributeProductEntity entity = new AttributeProductEntity();
        entity.setCamId(Long.valueOf(requestAdd.getCam()));
        entity.setChipId(Long.valueOf(requestAdd.getChip()));
        entity.setOsId(Long.valueOf(requestAdd.getOperatingSystem()));
        entity.setPinId(Long.valueOf(requestAdd.getPin()));
        entity.setRamID(Long.valueOf(requestAdd.getRam()));
        entity.setScreenId(Long.valueOf(requestAdd.getScreen()));
        entity.setProductId(productId);
        entity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
       try {
           repo.save(entity);
       }catch (Exception e){
           e.printStackTrace();
           return "false";
       }
        return "ok";
    }

    @Override
    public String updateAttribute(AttributeRequestEdit attributeRequestEdit , Long productId) {
        AttributeProductEntity entity = repo.findByProductId(productId);
        entity.setScreenId(Long.valueOf(attributeRequestEdit.getScreen()));
        entity.setRamID(Long.valueOf(attributeRequestEdit.getRam()));
        entity.setPinId(Long.valueOf(attributeRequestEdit.getPin()));
        entity.setOsId(Long.valueOf(attributeRequestEdit.getOperatingSystem()));
        entity.setCamId(Long.valueOf(attributeRequestEdit.getCam()));
        entity.setChipId(Long.valueOf(attributeRequestEdit.getChip()));
        entity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
        try {
            repo.save(entity);
        }catch (Exception e){
            return "false";
        }
        return "ok";
    }
}
