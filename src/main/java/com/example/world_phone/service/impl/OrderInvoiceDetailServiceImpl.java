package com.example.world_phone.service.impl;


import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.dto.request.orderinvoicedetail.OrderInvoiceDetailRequest;
import com.example.world_phone.entity.ColorEntity;
import com.example.world_phone.entity.InvoiceOrderDetailEntity;
import com.example.world_phone.entity.InvoiceOrderEntity;
import com.example.world_phone.entity.RomEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.ColorRepo;
import com.example.world_phone.repo.InvoiceOrderDetailRepo;
import com.example.world_phone.repo.InvoiceOrderRepo;
import com.example.world_phone.repo.RomRepo;
import com.example.world_phone.service.IColorService;
import com.example.world_phone.service.IOrderInvoiceDetailService;
import com.example.world_phone.service.IRomService;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderInvoiceDetailServiceImpl implements IOrderInvoiceDetailService {
    private final RomRepo romRepo;
    private final ColorRepo colorRepo;

    private final InvoiceOrderDetailRepo detailRepo;

    private final SessionUtil sessionUtil;

    private final InvoiceOrderRepo orderRepo;



    @Override
    public String createOrderInvoiceDetail(List<OrderInvoiceDetailRequest> list, InvoiceOrderEntity entity) {
        for (OrderInvoiceDetailRequest req: list
             ) {
            InvoiceOrderDetailEntity entityDetail = mapToEntity(req);
            entityDetail.setInvoiceOrderEntity(entity);
            try {
                InvoiceOrderDetailEntity temp = detailRepo.save(entityDetail);
            }catch (Exception e){
                log.error("Error add order detail : {}", e.getMessage());
                entity.setDeleteFlag(true);
                orderRepo.save(entity);
                return "false";
            }
        }
        return "ok";
    }


    private InvoiceOrderDetailEntity mapToEntity(OrderInvoiceDetailRequest request){
        InvoiceOrderDetailEntity entity = new InvoiceOrderDetailEntity();
        entity.setQuantityInvoice(request.getQuantityInvoice());

        // Tìm theo mã màu
        ColorEntity colorEntity = colorRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getColorId()));
        if(colorEntity == null){
            throw new WorldPhoneExp(ConstansErrorCode.COLOR_NOT_EXIST);
        }
        entity.setColorEntity(colorEntity);

        // tìm theo bộ nhớ trong của điện thoại
        RomEntity romEntity = romRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getRomId()));
        if(romEntity == null){
            throw new WorldPhoneExp(ConstansErrorCode.ROM_NOT_EXIST);
        }
        entity.setRomEntity(romEntity);
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        entity.setDeleteFlag(false);
        return entity;
    }
}
