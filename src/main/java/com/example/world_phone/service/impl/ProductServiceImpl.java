package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.product.ProductRequestAdd;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.entity.CategoryEntity;
import com.example.world_phone.entity.ImageEntity;
import com.example.world_phone.entity.ProductEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.AttributeRepo;
import com.example.world_phone.repo.ImageRepo;
import com.example.world_phone.repo.ProductRepo;
import com.example.world_phone.repo.PropertyProductRepo;
import com.example.world_phone.service.*;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
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
    private final ICategoryService categoryService;
    private final SessionUtil sessionUtil;
    private final IImageService iImageService;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<ProductResponse> findAll() {
        List<ProductEntity> productEntityList = productRepo.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (ProductEntity a : productEntityList){
            productResponseList.add(mapToRespone(a));
        }
        return productResponseList;
    }

    @Override
    public String createProduct(ProductRequestAdd requestProduct) {
        if(requestProduct.getRomRequestAdds().size() == 0){
            throw new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST);
        }
        ProductEntity entity = mapToRequest(requestProduct);
        entity = productRepo.save(entity);
        if(!attributeService.createAttribute(requestProduct.getAttributeRequestAdd(), entity.getId()).equals("ok")){
            return "that bai";
        }
        if(requestProduct.getImage().size() > 1){
            if(!iImageService.createImage(requestProduct.getImage(), entity.getId()).equals("ok")){
                return "that bai";
            }
        }

        if(!romService.createRom(requestProduct.getRomRequestAdds(), entity).equals("ok")){
            return "that bai";
        }
        return "ok";

    }


    // map tu entity ve dto
    private ProductResponse mapToRespone(ProductEntity x){
        if(x == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST);
        }else {
            ProductResponse response = new ProductResponse();
            response.setId(x.getId());
            response.setNote(x.getNote());
            response.setImageProduct(x.getImage_key());
            response.setCreate_By(x.getCreateBy());
            response.setCreate_Date(new Date(x.getCreateDate().getTime()));
            response.setNameProduct(x.getName());
            response.setStatus(x.getStatus());
            List<ImageEntity> imageEntities = imageRepo.findByProductEntity(response.getId());
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

    //map to dto ve entity
    private ProductEntity mapToRequest(ProductRequestAdd x){
        if(x == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST);
        }
        ProductEntity entity = new ProductEntity();
        entity.setNote(x.getNote());
        entity.setImage_key(x.getImage().get(0));
        CategoryEntity categoryEntity = categoryService.findById(String.valueOf(x.getCategoryId()));
        if(categoryEntity == null){
            throw new WorldPhoneExp(ConstansErrorCode.CATEGORY_NOT_EXIST);
        }
        entity.setCategory(categoryEntity);
        entity.setName(x.getNameProduct());
        entity.setStatus("ON");
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        entity.setDeleteFlag(false);
        return entity;
    }





}
