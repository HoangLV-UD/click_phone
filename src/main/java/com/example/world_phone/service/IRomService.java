package com.example.world_phone.service;

import com.example.world_phone.dto.request.rom.RomRequestAdd;
import com.example.world_phone.dto.respone.rom.RomRespone;
import com.example.world_phone.entity.ProductEntity;

import java.util.List;

public interface IRomService {
    List<RomRespone> findByProduct(Long id);

    String createRom(List<RomRequestAdd> romRequestAdds, ProductEntity entity);
}
