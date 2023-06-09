package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.attribute.pin.PinRequest;
import com.example.world_phone.dto.respone.attribute.pin.PinRespone;
import com.example.world_phone.entity.PinEntity;
import com.example.world_phone.repo.PinRepo;
import com.example.world_phone.service.IPinService;
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
public class PinServiceImpl implements IPinService {
    private final PinRepo repo;
    private final SessionUtil sessionUtil;
    @Override
    public List<PinRespone> findAll() {

        List<PinRespone> list = new ArrayList<>();
        List<PinEntity> entities = repo.findByDeleteFlagIsFalse();
        for (PinEntity e: entities
        ) {
            list.add(new PinRespone(e.getId(), e.getName()));
        }
        return list;
    }

    @Override
    public String addPin(PinRequest request) {
        PinEntity entity = new PinEntity();
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
    public String editPin(PinRequest request) {
        PinEntity entity = repo.getById(request.getId());
        entity.setName(request.getName());
        repo.save(entity);
        return "ok";
    }

    @Override
    public String delete(Long id) {
        PinEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }

    @Override
    public PinRespone findById(Long id) {
        PinEntity entity = repo.getById(id);
        return new PinRespone(entity.getId(), entity.getName());
    }
}
