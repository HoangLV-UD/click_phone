package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.ram.RamRequest;
import com.example.world_phone.dto.request.attribute.screen.ScreenRequest;
import com.example.world_phone.dto.respone.attribute.ram.RamRespone;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;

import java.util.List;

public interface IRamService {
    List<RamRespone> findAll();

    String save(RamRequest request);

    String edit(RamRequest request);

    String delete(Long id);

    RamRespone findById(String id);

}
