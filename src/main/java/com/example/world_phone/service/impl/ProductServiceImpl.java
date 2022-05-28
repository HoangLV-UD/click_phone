package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.product.ProductRequestAdd;
import com.example.world_phone.dto.request.product.ProductRequestEdit;
import com.example.world_phone.dto.respone.category.CategoryResponeDto;
import com.example.world_phone.dto.respone.image.ImageRespone;
import com.example.world_phone.dto.respone.product.ProductPropertyRespone;
import com.example.world_phone.dto.respone.product.ProductResponse;
import com.example.world_phone.dto.respone.rom.RomRespone;
import com.example.world_phone.entity.*;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.AttributeRepo;
import com.example.world_phone.repo.ImageRepo;
import com.example.world_phone.repo.ProductRepo;
import com.example.world_phone.repo.PropertyProductRepo;
import com.example.world_phone.service.*;
import com.example.world_phone.until.ConvertUtil;
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

    private final ConvertUtil convertUtil;

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
            log.error(String.valueOf(new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST).getErrorMessage().getVn()));
            return String.valueOf(new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST).getErrorMessage().getVn());
        }
        ProductEntity entity = mapToRequest(requestProduct);
        entity = productRepo.save(entity);
        if(!attributeService.createAttribute(requestProduct.getAttributeRequestAdd(), entity.getId()).equals("ok")){
            entity.setDeleteFlag(true);
            entity = productRepo.save(entity);
            log.error("them moi san pham that bai");
            return "that bai";
        }
        if(requestProduct.getImage().size() > 1){
            if(!iImageService.createImage(requestProduct.getImage(), entity.getId()).equals("ok")){
                entity.setDeleteFlag(true);
                entity = productRepo.save(entity);
                log.error("them moi san pham that bai");
                return "that bai";
            }
        }

        if(!romService.createRom(requestProduct.getRomRequestAdds(), entity).equals("ok")){
            entity.setDeleteFlag(true);
            entity = productRepo.save(entity);
            log.error("them moi san pham that bai");
            return "that bai";
        }
        return "ok";

    }

    @Override
    public String editProduct(ProductRequestEdit requestEdit) {
        if(requestEdit.getRomRequestAdds().size() == 0){
            throw new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST);
        }
        if(requestEdit.getIdProduct() == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST);
        }
        ProductEntity entity = productRepo.findByIdAndDeleteFlagIsFalse(requestEdit.getIdProduct());
        if(!attributeService.updateAttribute(requestEdit.getAttributeRequestedit()).equals("ok")){
            log.error("update sản phẩm thất bại ở phần attribute");
            return "false";
        }

        if(!romService.createRomWithProductEdit(requestEdit.getRomRequestAdds(), entity).equals("ok")){
            entity.setDeleteFlag(true);
            log.error("update sản phẩm thất bại ở phần rom");
            return "false";
        }
        entity.setName(requestEdit.getNameProduct());
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        productRepo.save(entity);
        return "ok";
    }

    @Override
    public ProductResponse getProduct(Long id) {
        ProductEntity entity = productRepo.findByIdAndDeleteFlagIsFalse(id);
        if(entity == null){
            throw new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST);
        }
        ProductResponse response = mapToRespone(entity);
        List<RomEntity> romEntityList = entity.getRomEntities();
        List<RomRespone> romRespones = new ArrayList<>();
        for (RomEntity r: romEntityList
        ) {
            RomRespone romRespone = new RomRespone();
            romRespone.setName(r.getName());
            romRespone.setId(String.valueOf(r.getId()));
            List<ProductPropertyRespone> productPropertyResponeList = new ArrayList<>();
            for (ProductPropertyEntity p: r.getProductProperties()
            ) {
                ProductPropertyRespone productPropertyRespone = new ProductPropertyRespone();
                productPropertyRespone.setQuantity(p.getQuantity());
                productPropertyRespone.setPrice(p.getPrice());
                productPropertyRespone.setPriceString(convertUtil.moneyToStringFormat(p.getPrice()));
                productPropertyRespone.setColor(p.getColor());
                productPropertyResponeList.add(productPropertyRespone);
            }
            romRespone.setProductPropertyResponeList(productPropertyResponeList);
            romRespones.add(romRespone);
        }
        response.setRomRespones(romRespones);
        return response;
    }

    @Override
    public String deleteProduct(Long id) {
        ProductEntity entity = productRepo.findByIdAndDeleteFlagIsFalse(id);
        if(entity == null){
            return String.valueOf(new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST).getErrorMessage().getVn());
        }
        entity.setDeleteFlag(true);
        productRepo.save(entity);
        return "ok";
    }

    @Override
    public String editStatusProduct(Long id, String value) {
        ProductEntity entity = productRepo.findByIdAndDeleteFlagIsFalse(id);
        if(entity == null){
            return String.valueOf(new WorldPhoneExp(ConstansErrorCode.PRODUCT_NOT_EXIST).getErrorMessage().getVn());
        }
        entity.setStatus(value);
        productRepo.save(entity);
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

            // tìm category
            CategoryEntity categoryEntity = x.getCategory();
            CategoryResponeDto categoryResponeDto = new CategoryResponeDto();
            categoryResponeDto.setCategoryId(String.valueOf(categoryEntity.getId()));
            categoryResponeDto.setCategoryName(categoryEntity.getName());
            response.setCategoryResponeDto(categoryResponeDto);


            List<ImageEntity> imageEntities = imageRepo.findByProductEntity(response.getId());
            List<ImageRespone> imageProduct = new ArrayList<>();
            List<RomEntity> romEntityList = x.getRomEntities();
            List<RomRespone> romRespones = new ArrayList<>();
            for (RomEntity r: romEntityList
                 ) {
                RomRespone romRespone = new RomRespone();
                romRespone.setName(r.getName());
                romRespone.setId(String.valueOf(r.getId()));
                List<ProductPropertyRespone> productPropertyResponeList = new ArrayList<>();
                for (ProductPropertyEntity p: r.getProductProperties()
                     ) {
                    ProductPropertyRespone productPropertyRespone = new ProductPropertyRespone();
                    productPropertyRespone.setQuantity(p.getQuantity());
                    productPropertyRespone.setPrice(p.getPrice());
                    productPropertyRespone.setPriceString(convertUtil.moneyToStringFormat(p.getPrice()));
                    productPropertyRespone.setColor(p.getColor());
                    productPropertyResponeList.add(productPropertyRespone);
                }
                romRespone.setProductPropertyResponeList(productPropertyResponeList);
                romRespones.add(romRespone);
            }

            for (ImageEntity a: imageEntities
                 ) {
                ImageRespone imageRespone = new ImageRespone();
                imageRespone.setImg_link(a.getLing_image());
                imageRespone.setId(String.valueOf(a.getId()));
                imageProduct.add(imageRespone);
            }


            response.setRomRespones(romRespones);
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
