package com.example.world_phone.service.impl;


import com.example.world_phone.dto.respone.cate.CateRespone;
import com.example.world_phone.dto.respone.category.CategoryDTO;
import com.example.world_phone.dto.respone.category.CategoryResponeDto;
import com.example.world_phone.entity.CategoryEntity;
import com.example.world_phone.repo.CateRepo;
import com.example.world_phone.service.ICateService;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CateServiceImpl implements ICateService {
    private final CateRepo repo;
    private final SessionUtil sessionUtil;

    private <R> CategoryResponeDto mapToCategoryResp(CategoryEntity categoryEntity) {
        if (categoryEntity == null) return new CategoryResponeDto();
        return CategoryResponeDto.builder()
                .categoryId(String.valueOf(categoryEntity.getId()))
                .categoryName(categoryEntity.getName())
                .build();
    }


    @Override
    public List<CategoryResponeDto> findAll() {
        return repo.findByDeleteFlagIsFalseOrderByCreateDateAsc().stream()
                .map(this::mapToCategoryResp)
                .collect(Collectors.toList());
    }

    @Override
    public String save(CategoryDTO request) {
        Date now = new Date();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(request.getName());
        categoryEntity.setCreateDate(new Timestamp(now.getTime()));
        categoryEntity.setCreateBy((String) sessionUtil.getObject("username"));
        categoryEntity.setModifierBy((String) sessionUtil.getObject("username"));
        categoryEntity.setModifierDate(new Timestamp(now.getTime()));
        categoryEntity.setStatus("1");
        categoryEntity.setDeleteFlag(false);
        try {
            repo.save(categoryEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public String edit(CategoryDTO request) {
        Date now = new Date();
        CategoryEntity entity = repo.getById(Long.valueOf(request.getId()));
        entity.setName(request.getName());
        entity.setModifierBy("Admin");
        entity.setModifierDate(new Timestamp(now.getTime()));
        try {
            repo.save(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }
//        entity.setLoai(request.getLoai());
//        entity.setName(request.getCategoryName());
//        entity.setCreateDate(new Timestamp(now.getTime()));
//        entity.setCreateBy("Admin");
//        entity.setModifierBy("Admin");
//        entity.setModifierDate(new Timestamp(now.getTime()));
//        entity.setStatus("1");
//        entity.setDeleteFlag(false);
//        repo.save(entity);
//        return "ok";
//    }

    @Override
    public CategoryResponeDto findById(String id) {
        CategoryEntity entity = repo.findByIdAndDeleteFlagIsFalse(Long.valueOf(id)).get(0);
        return new CategoryResponeDto(String.valueOf(entity.getId()), entity.getName());
    }

    @Override
    public String delete(Long id) {
        CategoryEntity entity = repo.getById(id);
        entity.setDeleteFlag(true);
        repo.save(entity);
        return "ok";
    }
}
