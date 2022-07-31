package com.example.world_phone.service.impl;


import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.constant.ConstansStatus;
import com.example.world_phone.dto.request.product_property.ProductPropertyRequest;
import com.example.world_phone.dto.respone.product.ProductPropertyRespone;
import com.example.world_phone.entity.ProductPropertyEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.PropertyProductRepo;
import com.example.world_phone.service.IProductPropertyService;
import com.example.world_phone.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductPropertyServiceImpl implements IProductPropertyService {
    private final PropertyProductRepo repo;
    private final ConvertUtil convertUtil;

    @Override
    public List<ProductPropertyRespone> findByRom(Long id) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductPropertyEntity> entityList = repo.findByRom(id);
        for (ProductPropertyEntity a: entityList
             ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }

    @Override
    public List<ProductPropertyRespone> findByRomAndColor(String romId, String colorId) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductPropertyEntity> entityList = repo.findByRomAndColor(Long.valueOf(romId), Long.valueOf(colorId));
        for (ProductPropertyEntity a: entityList
        ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }

    @Override
    public List<ProductPropertyRespone> findByColor(String colorId) {
        List<ProductPropertyRespone> respones = new ArrayList<>();
        List<ProductPropertyEntity> entityList = repo.findByColor(Long.valueOf(colorId));
        for (ProductPropertyEntity a: entityList
        ) {
            respones.add(mapToDto(a));
        }
        return respones;
    }

    @Override
    public ProductPropertyRespone updateProductProperty(ProductPropertyRequest request) {
        ProductPropertyEntity entity = repo.findByRomAndColor(Long.valueOf(request.getRomId()), Long.valueOf(request.getColorId())).get(0);
        entity.setPrice(Long.parseLong(request.getPrice()));
        entity.setQuantity(Long.parseLong(request.getQuantity()));
        ProductPropertyRespone respone = mapToDto(entity);
        try {
            repo.save(entity);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
        return respone;
    }

    @Override
    public String udpateStatusProductProperty(ProductPropertyRequest request) {

        List<ProductPropertyEntity> entityList = repo.findByRomAndColor(Long.valueOf(request.getRomId()), Long.valueOf(request.getColorId()));
        if(entityList.size() < 1){
            log.error(String.valueOf(new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST).getErrorMessage().getVn()));
            return "false";
        }
        if(request.getStatus().equalsIgnoreCase("Ngừng kinh doanh")){
            entityList.get(0).setStatus(ConstansStatus.OFF);
        }else {
            entityList.get(0).setStatus(ConstansStatus.ON);
        }

        repo.save(entityList.get(0));
        return "ok";
    }




    private ProductPropertyRespone mapToDto(ProductPropertyEntity entity){
        if(entity == null){
            return null;
        }else {
            ProductPropertyRespone dto = new ProductPropertyRespone();
            dto.setColorName(entity.getColorEntity().getValueColor());
            dto.setRomId(String.valueOf(entity.getRomEntity().getId()));
            dto.setColorId(String.valueOf(entity.getColorEntity().getId()));
            dto.setId(String.valueOf(entity.getId()));
            dto.setPrice(entity.getPrice());
            dto.setQuantity(entity.getQuantity());
            dto.setStatus(entity.getStatus());
            dto.setPriceString(convertUtil.moneyToStringFormat(entity.getPrice()));
            dto.setPricePromotion(entity.getPricePromotion());
            dto.setPricePromotionString(convertUtil.moneyToStringFormat(entity.getPricePromotion()));
            return dto;
        }
    }
}
