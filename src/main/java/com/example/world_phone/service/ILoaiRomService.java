package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.loai_rom.Loai_RomRequest;
import com.example.world_phone.dto.request.attribute.loai_screen.Loai_ScreenRequest;
import com.example.world_phone.dto.respone.attribute.loai_rom.Loai_RomRespone;
import com.example.world_phone.dto.respone.attribute.loai_screen.Loai_ScreenRespone;

import java.util.List;

public interface ILoaiRomService {
    List<Loai_RomRespone> findAll();

    Loai_RomRespone findById(String id);

    String createLoaiRom(Loai_RomRequest request);

    String updateLoaiRom(Loai_RomRequest request);

    String deleteLoaiRom(Long id);
}
