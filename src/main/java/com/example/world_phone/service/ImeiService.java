package com.example.world_phone.service;

import com.example.world_phone.dto.request.imei.ImeiRequest;
import com.example.world_phone.dto.respone.imei.ImeiResponse;

import java.util.List;


public interface ImeiService {
    boolean saveImei(List<ImeiRequest> list, Long idProductOrder);

    List<ImeiResponse> findImeiDaBan(Long product, Long order);
}
