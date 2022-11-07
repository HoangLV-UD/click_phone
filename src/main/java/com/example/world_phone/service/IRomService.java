package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.ram.RamRequest;
import com.example.world_phone.dto.request.attribute.rom.RomRequest;
import com.example.world_phone.dto.request.rom.RomRequestAdd;
import com.example.world_phone.dto.respone.attribute.ram.RamRespone;
import com.example.world_phone.dto.respone.rom.RomRespone;
import com.example.world_phone.entity.ProductEntity;

import java.util.List;

public interface IRomService {
    List<RomRespone> findByProduct(Long id);

    String createRom(List<RomRequestAdd> romRequestAdds, ProductEntity entity);

    String createRomWithProductEdit(List<RomRequestAdd> romRequestAdds, ProductEntity entity);
    List<RomRespone> findAll();

    String saveRom(RomRequest request);

    String editRom(RomRequest request);

    RamRespone findById(String id);

    String deleteRom(Long id);
}
