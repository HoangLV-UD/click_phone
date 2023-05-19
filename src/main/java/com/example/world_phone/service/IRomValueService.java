package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.rom.RomRequest;
import com.example.world_phone.dto.respone.attribute.rom.RomRespone;

import java.util.List;


public interface IRomValueService {
    List<RomRespone> findAll();
    String save(RomRequest request);
    String update(RomRequest request);
    String delete(Long id);
    RomRespone findById(Long id);
}
