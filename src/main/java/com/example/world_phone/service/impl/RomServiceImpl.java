package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.rom.RomRequestAdd;
import com.example.world_phone.dto.respone.rom.RomRespone;
import com.example.world_phone.entity.ProductEntity;
import com.example.world_phone.entity.RomEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.RomRepo;
import com.example.world_phone.service.IProductPropertyService;
import com.example.world_phone.service.IRomService;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RomServiceImpl implements IRomService {
    private final RomRepo romRepo;

    private final IProductPropertyService productPropertyService;
    private final SessionUtil sessionUtil;


    @Override
    public List<RomRespone> findByProduct(Long id) {
        List<RomEntity> romEntityList = romRepo.findByProductEntity(id);
        List<RomRespone> romRespones = new ArrayList<>();
        for (RomEntity r: romEntityList
             ) {
            romRespones.add(new RomRespone(String.valueOf(r.getId()), r.getName(), null));
        }
        return romRespones;
    }

    @Override
    public String createRom(List<RomRequestAdd> romRequestAdds, ProductEntity entityPro) {
        for (RomRequestAdd a: romRequestAdds
             ) {
            RomEntity entity = mapToEntity(a);
            entity.setProductEntity(entityPro);
            romRepo.save(entity);
        }
        return "ok";
    }

    @Override
    public String createRomWithProductEdit(List<RomRequestAdd> romRequestAdds, ProductEntity entity) {
        List<RomEntity> entities = romRepo.findByProductEntity(entity.getId());
        for (RomRequestAdd a: romRequestAdds
        ) {
            int check = 0;
            for (RomEntity e: entities
                 ) {
                if(a.getNameRom().equals(e.getName())){
                    check = 1;
                    break;
                }
            }
            if(check == 0){
                RomEntity entity1 = mapToEntity(a);
                entity1.setProductEntity(entity);
                romRepo.save(entity1);
            }
        }
        return "ok";
    }

    private RomRespone mapToDTO(RomEntity romEntity){
        if(romEntity == null){
            throw new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST);
        } else {
            RomRespone respone = new RomRespone();
            respone.setName(romEntity.getName());
            respone.setProductPropertyResponeList(productPropertyService.findByRom(romEntity.getId()));
            return respone;
        }
    }

    public  RomEntity mapToEntity(RomRequestAdd a){
        RomEntity entity = new RomEntity();
        entity.setStatus("ON");
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        entity.setDeleteFlag(false);
        entity.setName(a.getNameRom());
        return entity;
    }
}
