package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.attribute.rom.RomRequest;
import com.example.world_phone.dto.respone.attribute.rom.RomRespone;
import com.example.world_phone.entity.LoaiRomEntity;
import com.example.world_phone.entity.RomValueEntity;
import com.example.world_phone.repo.RomValueRepo;
import com.example.world_phone.service.IRomValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 07/07/2022
 * Project_name: com.example.world_phone.service.impl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class IRomValueServiceImpl implements IRomValueService {
    private final RomValueRepo repo;
    @Override
    public List<RomRespone> findAll() {
        List<RomRespone> list = new ArrayList<>();
        List<RomValueEntity> entities = repo.findByDeleteFlagIsFalse();
        for (RomValueEntity e: entities
             ) {
            list.add(new RomRespone(e.getId(), e.getName() + " " + e.getLoaiRomEntity().getName(), e.getLoaiRomEntity().getId()));
        }
        return list;
    }

    @Override
    public String save(RomRequest request) {
        RomValueEntity entity = new RomValueEntity();
        LoaiRomEntity loaiRomEntity = new LoaiRomEntity();
        loaiRomEntity.setId(request.getLoaiRomId());
        entity.setId(request.getId());
        entity.setLoaiRomEntity(loaiRomEntity);
        entity.setName(request.getName());
        repo.save(entity);
        return "ok";
    }

    @Override
    public String update(RomRequest request) {
        RomValueEntity entity = repo.getById(request.getId());
        LoaiRomEntity loaiRomEntity = new LoaiRomEntity();
        loaiRomEntity.setId(request.getLoaiRomId());
        entity.setLoaiRomEntity(loaiRomEntity);
        entity.setName(request.getName());
        repo.save(entity);
        return "ok";
    }

    @Override
    public String delete(Long id) {
        RomValueEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }

    @Override
    public RomRespone findById(Long id) {
        RomValueEntity entity = repo.getById(id);
        System.out.println("sssssssssssss"+entity);
        return new RomRespone(entity.getId(), entity.getName(),entity.getLoaiRomEntity().getId());
    }
}
