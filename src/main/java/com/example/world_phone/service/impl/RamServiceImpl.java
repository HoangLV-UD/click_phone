package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.attribute.ram.RamRequest;
import com.example.world_phone.dto.respone.attribute.ram.RamRespone;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;
import com.example.world_phone.entity.RamEntity;
import com.example.world_phone.repo.RamRepo;
import com.example.world_phone.service.IRamService;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RamServiceImpl implements IRamService {
    private final RamRepo repo;
    private final SessionUtil sessionUtil;
    @Override
    public List<RamRespone> findAll() {
        List<RamRespone> list = new ArrayList<>();
        List<RamEntity> entities = repo.findByDeleteFlagIsFalse();
        for (RamEntity e: entities
             ) {
            list.add(new RamRespone(e.getId(), e.getName()));
        }
        return list;
    }
    @Override
    public String edit(RamRequest request) {
        RamEntity entity = repo.getById(request.getId());
        entity.setName(request.getName());
        repo.save(entity);
        return "ok";
    }
    @Override
    public String save(RamRequest request) {
        RamEntity entity = new RamEntity();
        entity.setName(request.getName());
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy("ADMIN");
        entity.setDeleteFlag(false);
        repo.save(entity);
        return "ok";
    }
    @Override
    public String delete(Long id) {
        RamEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }
    @Override
    public RamRespone findById(String id) {
        RamEntity entity = repo.getById(Long.valueOf(id));
        return new RamRespone(entity.getId(), entity.getName());
    }
}
