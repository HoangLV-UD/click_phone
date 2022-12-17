package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.pin.PinRequest;
import com.example.world_phone.dto.respone.attribute.pin.PinRespone;

import java.util.List;

public interface IPinService {

    List<PinRespone> findAll();

    String addPin(PinRequest request);

    String editPin(PinRequest request);

    String delete(Long id);

    PinRespone findById(Long id);
}
