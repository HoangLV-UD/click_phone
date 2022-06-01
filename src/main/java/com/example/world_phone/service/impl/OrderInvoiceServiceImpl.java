package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.entity.InvoiceOrderEntity;
import com.example.world_phone.entity.StaffEntity;
import com.example.world_phone.entity.SupplierEntity;
import com.example.world_phone.exception.WorldPhoneExp;
import com.example.world_phone.repo.InvoiceOrderRepo;
import com.example.world_phone.repo.StaffRepo;
import com.example.world_phone.repo.SupplierRepo;
import com.example.world_phone.service.IOrderInvoiceDetailService;
import com.example.world_phone.service.IOrderInvoiceService;
import com.example.world_phone.until.ConvertUtil;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderInvoiceServiceImpl implements IOrderInvoiceService {

    private final InvoiceOrderRepo orderRepo;

    private final SupplierRepo supplierRepo;

    private final SessionUtil sessionUtil;

    private final ConvertUtil convertUtil;

    private final StaffRepo staffRepo;

    private final IOrderInvoiceDetailService detailService;



    @Override
    public String createOrderInvoice(OrderInvoiceRequest request) {
        InvoiceOrderEntity entity = mapRequestToEntity(request);
        entity = orderRepo.save(entity);
        if(entity != null){
            String checkDetail = detailService.createOrderInvoiceDetail(request.getDetailRequest(), entity);
            return "ok";
        }
        return "false";
    }

    private InvoiceOrderEntity mapRequestToEntity(OrderInvoiceRequest request){
        InvoiceOrderEntity entity = new InvoiceOrderEntity();
        entity.setStatus(1);
        entity.setCodeOrder(request.getOrderCode());
        Date date = convertUtil.strToDate(request.getReceiveDate(), "dd-MM-yyyy");
        entity.setReceiveDate(date);
        Optional<SupplierEntity> supplierEntity = supplierRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getSuppliderId()));
        if(!supplierEntity.isPresent()){
            log.error("Supplier not exist");
            throw new WorldPhoneExp(ConstansErrorCode.SUPPLIER_NOT_EXIST);
        }
        entity.setSupplierEntity(supplierEntity.get());
        if(request.getNote() != null){
            entity.setNote(request.getNote());
        }
        StaffEntity staffEntity = staffRepo.findByEmailAndDeleteFlagIsFalse(String.valueOf(sessionUtil.getObject("username"))).get(0);
        entity.setStaffEntity(staffEntity);
        entity.setCreateBy((String) sessionUtil.getObject("username"));
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy((String) sessionUtil.getObject("username"));
        entity.setDeleteFlag(false);
        return entity;
    }
}
