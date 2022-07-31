package com.example.world_phone.service;


import com.example.world_phone.dto.request.promotion.PromotionRequestDTO;
import com.example.world_phone.dto.respone.promotion.PromotionResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPromotionService {

    List<PromotionResponseDTO> getAll();

    PromotionResponseDTO findById(String id);


    String create(PromotionRequestDTO promotionRequestDTO);

    PromotionResponseDTO findByProductId(String productId);

    String update(PromotionRequestDTO promotionRequestDTO);

    void delete(Long id);

    double getPromotionProduct(String id);

    void activePromotion(String id);

    void inActivePromotion(String id);
}
