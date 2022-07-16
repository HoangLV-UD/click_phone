package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.attribute.chip.ChipRequest;
import com.example.world_phone.dto.respone.attribute.chip.ChipRespone;
import com.example.world_phone.entity.ChipEntity;
import com.example.world_phone.repo.ChipRepo;
import com.example.world_phone.service.IChipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 03/07/2022
 * Project_name: com.example.world_phone.service.impl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ChipServiceImpl implements IChipService {
    private final ChipRepo repo;


    @Override
    public List<ChipRespone> findAll() {
        List<ChipRespone> list = new ArrayList<>();
        List<ChipEntity> entities = repo.findByDeleteFlagIsFalse();
        for (ChipEntity e: entities
             ) {
            list.add(new ChipRespone(String.valueOf(e.getId()), e.getName()));
        }
        return list;
    }

    @Override
    public ChipRespone findById(String id) {
        ChipEntity chip = repo.getById(Long.valueOf(id));
        return new ChipRespone(String.valueOf(chip.getId()), chip.getName());
    }

    @Override
    public String createChip(ChipRequest request) {
        ChipEntity chip = new ChipEntity();
        chip.setName(request.getName());
        repo.save(chip);
        return "ok";
    }

    @Override
    public String updateChip(ChipRequest request) {
        ChipEntity chip = new ChipEntity();
        chip.setName(request.getName());
        chip.setId(Long.valueOf(request.getId()));
        repo.save(chip);
        return "ok";
    }

    @Override
    public String deleteChip(Long id) {
        ChipEntity chip = repo.getById(id);
       chip.setDeleteFlag(true);
        repo.save(chip);
        return "ok";
    }
}
