package com.example.world_phone.service;

import com.example.world_phone.dto.request.attribute.screen.ScreenRequest;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;

import java.util.List;

public interface IScreenService {

     List<ScreenReposne> findAllScreen();

     String save(ScreenRequest request);

     String edit(ScreenRequest request);

     ScreenReposne findById(String id);

     String delete(Long id);
}
