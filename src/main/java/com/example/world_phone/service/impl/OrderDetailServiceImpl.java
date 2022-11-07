package com.example.world_phone.service.impl;

import com.example.world_phone.entity.OrdersDetailEntity;
import com.example.world_phone.entity.OrdersEntity;
import com.example.world_phone.entity.ProductPropertyEntity;
import com.example.world_phone.repo.OrdersDetailRepo;
import com.example.world_phone.repo.OrdersRepo;
import com.example.world_phone.repo.PropertyProductRepo;
import com.example.world_phone.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 06/09/2022
 * Project_name: com.example.world_phone.service.impl
 */
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {
    private final OrdersDetailRepo detailRepo;

    private final OrdersRepo ordersRepo;

    private  final PropertyProductRepo propertyProductRepo;
    @Override
    public String deleteDetail(Long id, String codeOrder) {
        OrdersDetailEntity detailEntity = detailRepo.findByDeleteFlagIsFalseAndId(id);
        detailEntity.setDeleteFlag(true);
        ProductPropertyEntity entity = propertyProductRepo.findById(detailEntity.getIdPropertyProduct()).get();
        entity.setQuantity(detailEntity.getQuantity() + entity.getQuantity());
        propertyProductRepo.save(entity);
        detailRepo.save(detailEntity);
        return "ok";
    }
}
