package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.cam.CamRequest;
import com.example.world_phone.dto.respone.attribute.cam.CamRespone;

import java.util.List;


public interface ICamService {
    List<CamRespone> findAll();

    String save(CamRequest request);

    String update(CamRequest request);

    String delete(Long id);

    CamRespone findByID(Long id);
}
