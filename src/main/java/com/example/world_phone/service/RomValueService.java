package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.rom.RomRequest;
import com.example.world_phone.dto.respone.attribute.rom.RomRespone;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 07/07/2022
 * Project_name: com.example.world_phone.service
 */
public interface RomValueService {
    List<RomRespone> findAll();
    String save(RomRequest request);
    String update(RomRequest request);
    String delete(Long id);
    RomRespone findById(Long id);
}
