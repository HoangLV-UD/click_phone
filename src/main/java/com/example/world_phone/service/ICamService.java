package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.cam.CamRequest;
import com.example.world_phone.dto.respone.attribute.cam.CamRespone;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 03/07/2022
 * Project_name: com.example.world_phone.service
 */
public interface ICamService {
    List<CamRespone> findAll();

    String save(CamRequest request);

    String update(CamRequest request);

    String delete(Long id);

    CamRespone findByID(Long id);
}
