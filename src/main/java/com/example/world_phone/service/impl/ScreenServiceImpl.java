package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.attribute.screen.ScreenRequest;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;
import com.example.world_phone.entity.ScreenEntity;
import com.example.world_phone.repo.ScreenRepo;
import com.example.world_phone.service.IScreenService;
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
public class ScreenServiceImpl implements IScreenService {

    private final ScreenRepo repo;

    private SessionUtil sessionUtil;

    @Override
    public List<ScreenReposne> findAllScreen() {
        List<ScreenEntity> entityList = repo.findByDeleteFlagIsFalse();
        List<ScreenReposne> reposnes = new ArrayList<>();
        for (ScreenEntity e: entityList
             ) {
            ScreenReposne reposne = new ScreenReposne(e.getId(), e.getName(), e.getLoai());
            reposnes.add(reposne);
        }
        return reposnes;
    }

    @Override
    public String save(ScreenRequest request) {
        ScreenEntity entity = new ScreenEntity();
        entity.setName(request.getName());
        entity.setLoai(request.getLoai());
        entity.setCreateBy("ADMIN");
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy("ADMIN");
        entity.setDeleteFlag(false);
        repo.save(entity);
        return "ok";
    }

    @Override
    public String edit(ScreenRequest request) {
        ScreenEntity entity = repo.getById(request.getId());
        entity.setName(request.getName());
        entity.setLoai(request.getLoai());
        repo.save(entity);
        return "ok";
    }

    @Override
    public ScreenReposne findById(String id) {
        ScreenEntity entity = repo.getById(Long.valueOf(id));
        return new ScreenReposne(entity.getId(), entity.getName(), entity.getLoai());
    }

    @Override
    public String delete(Long id) {
        ScreenEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }

}
