package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.entity.ImageEntity;
import com.example.world_phone.entity.ProductEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.AttributeRepo;
import com.example.world_phone.repo.ImageRepo;
import com.example.world_phone.repo.ProductRepo;
import com.example.world_phone.repo.PropertyProductRepo;
import com.example.world_phone.service.IAttributeService;
import com.example.world_phone.service.IProductService;
import com.example.world_phone.service.IRomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepo productRepo;

    private final PropertyProductRepo propertyProductRepo;

    private final IAttributeService attributeService;

    private final IRomService romService;

    private final ImageRepo imageRepo;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<ProductResponse> findAll() {
        List<ProductEntity> productEntityList = productRepo.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (ProductEntity a : productEntityList){

        }
        return null;
    }



    // map tu entity ve dto
    private ProductResponse mapToRespone(ProductEntity x){
        if(x == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST);
        }else {
            ProductResponse response = new ProductResponse();
            response.setId(x.getId());
            response.setImageProduct(x.getImage_key());
            response.setCreate_By(x.getCreateBy());
            response.setCreate_Date(new Date(x.getCreateDate().getTime()));
            response.setNameProduct(x.getName());
            List<ImageEntity> imageEntities = imageRepo.findAll(response.getId());
            List<String> imageProduct = new ArrayList<>();
            for (ImageEntity a: imageEntities
                 ) {
                imageProduct.add(a.getLing_image());
            }
            response.setImage(imageProduct);
            response.setAttributeRespone(attributeService.findByProductId(response.getId()));
            response.setRomRespones(romService.findByProduct(x.getId()));
            return response;
        }
    }





}
