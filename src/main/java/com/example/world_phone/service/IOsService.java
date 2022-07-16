package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.os.OsRequest;
import com.example.world_phone.dto.request.attribute.screen.ScreenRequest;
import com.example.world_phone.dto.respone.attribute.os.OsRespone;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;

import java.util.List;

public interface IOsService {
    List<OsRespone> findAll();

    String save(OsRequest request);

    String edit(OsRequest request);

    OsRespone findById(String id);

    String delete(Long id);
}
