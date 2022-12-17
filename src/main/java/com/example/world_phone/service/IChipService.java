package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.chip.ChipRequestDto;
import com.example.world_phone.dto.respone.attribute.chip.ChipRespone;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 03/07/2022
 * Project_name: com.example.world_phone.service
 */
public interface IChipService {
    List<ChipRespone> findAll();

    ChipRespone findByCate(String id);

    String createChip(ChipRequestDto request);

    String updateChip(ChipRequestDto request);

    String deleteChip(Long idChip);
}
