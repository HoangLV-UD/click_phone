package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.respone.rom.RomRespone;
import com.example.world_phone.entity.RomEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.RomRepo;
import com.example.world_phone.service.IProductPropertyService;
import com.example.world_phone.service.IRomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RomServiceImpl implements IRomService {
    private final RomRepo romRepo;

    private final IProductPropertyService productPropertyService;


    @Override
    public List<RomRespone> findByProduct(Long id) {
        List<RomEntity> romEntityList = romRepo.findByProductEntity(id);
        return null;
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
}
