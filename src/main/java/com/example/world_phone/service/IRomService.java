package com.example.world_phone.service;

import com.example.world_phone.dto.respone.rom.RomRespone;

import java.util.List;

public interface IRomService {
    List<RomRespone> findByProduct(Long id);
}
