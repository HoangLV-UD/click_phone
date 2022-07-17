package com.example.world_phone.service.impl;

import com.example.world_phone.constant.ConstansErrorCode;
import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.dto.respone.order_invoice.OrderInvoiceRespone;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        entity.setTotalMoney(0L);
        entity = orderRepo.save(entity);
        if(entity != null){
            String checkDetail = detailService.createOrderInvoiceDetail(request.getDetailRequest(), entity);
            if(checkDetail.equals("ok")){
                return "ok";
            }

        }
        return "false";
    }

    @Override
    public String createNhap(OrderInvoiceRequest request) {
        if(request.getId().equals("0")){
            InvoiceOrderEntity entity = new InvoiceOrderEntity();
            entity.setTotalMoney(request.getTotalMoney());
            entity.setNote(request.getNote());
            entity.setReceiveDate(new Date());
            entity.setGiamGia(request.getDiscount());
            entity.setTotalMoneyThua(request.getTienThua());
            entity.setTotalMoneyPay(request.getPaid());
            entity.setStatus(2);
            entity.setCodeOrder("HD00");
            Optional<SupplierEntity> supplierEntity = supplierRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getSuppliderId()));
            if(!supplierEntity.isPresent()){
                log.error("Supplier not exist");
                throw new WorldPhoneExp(ConstansErrorCode.SUPPLIER_NOT_EXIST);
            }
            entity.setSupplierEntity(supplierEntity.get());
            StaffEntity staffEntity = staffRepo.findByEmailAndDeleteFlagIsFalse(String.valueOf(sessionUtil.getObject("username"))).get(0);
            entity.setStaffEntity(staffEntity);
            entity.setCreateBy("ADMIN");
            entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
            entity.setModifierBy("ADMIN");
            entity.setDeleteFlag(false);
            entity = orderRepo.save(entity);
            entity.setCodeOrder(entity.getCodeOrder() + entity.getId());
            orderRepo.save(entity);
            String check = detailService.createNhapDetail(request.getDetailRequest(), entity);
            if(!check.equals("ok")){
                return "false";
            }
            return "ok";
        }else {
            Optional<InvoiceOrderEntity> entity = orderRepo.findById(Long.valueOf(request.getId()));
            if(entity.isPresent()){
                Optional<SupplierEntity> supplierEntity = supplierRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getSuppliderId()));
                if(!supplierEntity.isPresent()){
                    log.error("Supplier not exist");
                    throw new WorldPhoneExp(ConstansErrorCode.SUPPLIER_NOT_EXIST);
                }
                entity.get().setSupplierEntity(supplierEntity.get());
                entity.get().setTotalMoney(request.getTotalMoney());
                entity.get().setNote(request.getNote());
                entity.get().setReceiveDate(new Date());
                entity.get().setGiamGia(request.getDiscount());
                entity.get().setTotalMoneyThua(request.getTienThua());
                entity.get().setTotalMoneyPay(request.getPaid());
                entity.get().setStatus(2);
                StaffEntity staffEntity = staffRepo.findByEmailAndDeleteFlagIsFalse(String.valueOf(sessionUtil.getObject("username"))).get(0);
                entity.get().setStaffEntity(staffEntity);
                InvoiceOrderEntity e = orderRepo.save(entity.get());
                String check = detailService.createNhapDetail(request.getDetailRequest(), e);
                if(!check.equals("ok")){
                    return "false";
                }
                return "ok";
            }


        }
        return null;
    }

    @Override
    public String updateNhap(OrderInvoiceRequest request) {
        Optional<InvoiceOrderEntity> entity = orderRepo.findById(Long.valueOf(request.getId()));
        if(entity.isPresent()){
            Optional<SupplierEntity> supplierEntity = supplierRepo.findByIdAndDeleteFlagIsFalse(Long.valueOf(request.getSuppliderId()));
            if(!supplierEntity.isPresent()){
                log.error("Supplier not exist");
                throw new WorldPhoneExp(ConstansErrorCode.SUPPLIER_NOT_EXIST);
            }
            entity.get().setSupplierEntity(supplierEntity.get());
            entity.get().setTotalMoney(request.getTotalMoney());
            entity.get().setNote(request.getNote());
            entity.get().setGiamGia(request.getDiscount());
            entity.get().setTotalMoneyThua(request.getTienThua());
            entity.get().setTotalMoneyPay(request.getPaid());
            StaffEntity staffEntity = staffRepo.findByEmailAndDeleteFlagIsFalse(String.valueOf(sessionUtil.getObject("username"))).get(0);
            entity.get().setStaffEntity(staffEntity);
            InvoiceOrderEntity e = orderRepo.save(entity.get());
            String check = detailService.updateNhapDetail(request.getDetailRequest(), e);
            if(!check.equals("ok")){
                return "false";
            }
            return "ok";
        }
        return null;
    }

    @Override
    public String updateOrderInvoice(OrderInvoiceRequest request) {
        InvoiceOrderEntity entity = orderRepo.findByCodeOrder(request.getOrderCode());
        if(entity != null){
            InvoiceOrderEntity entity1 = mapRequestToEntity(request);
            entity1.setId(entity.getId());
            orderRepo.save(entity1);
            String checkDetail = detailService.updateOrderInvoiceDetail(request.getDetailRequest(), entity);
            if(checkDetail.equals("ok")){
                return "ok";
            }

        }
        return null;
    }

    @Override
    public String findByCodeOrder(String code) {
        InvoiceOrderEntity entity = orderRepo.findByCodeOrder(code);
        if(entity != null){
            return "ok";
        }
        return "false";
    }

    @Override
    public List<OrderInvoiceRespone> finAll() {
        List<InvoiceOrderEntity> entityList = orderRepo.findByDeleteFlagIsFalse();
        List<OrderInvoiceRespone> list = new ArrayList<>();
        for (InvoiceOrderEntity e: entityList
             ) {
            list.add(mapToRespone(e));
        }
        return list;
    }

    @Override
    public List<OrderInvoiceRespone> finAllTotalMoney() {
        List<InvoiceOrderEntity> entityList = orderRepo.findByDeleteFlagIsFalseAndTotalMoney();
        List<OrderInvoiceRespone> list = new ArrayList<>();
        for (InvoiceOrderEntity e: entityList
        ) {
            list.add(mapToRespone(e));
        }
        return list;
    }

    @Override
    public String changeStatus(Long id, String message) {
        InvoiceOrderEntity entity = orderRepo.findByIdAndDeleteFlagIsFalse(id);
        if(message.equals("true")){
            entity.setStatus(0);
            orderRepo.save(entity);
        }else {
            entity.setStatus(1);
            orderRepo.save(entity);
        }
        return "ok";
    }

    @Override
    public OrderInvoiceRespone findById(Long id) {
        InvoiceOrderEntity entity = orderRepo.findByIdAndDeleteFlagIsFalse(id);
        return mapToRespone(entity);
    }

    private OrderInvoiceRespone mapToRespone(InvoiceOrderEntity entity){
        OrderInvoiceRespone respone = new OrderInvoiceRespone();
        respone.setId(entity.getId());
        respone.setStaffName(entity.getStaffEntity().getFullName());
        respone.setTotalMoneny(entity.getTotalMoney());
        respone.setTotalMonenyString(convertUtil.moneyToStringFormat(entity.getTotalMoney()));
        respone.setGiamGia(entity.getGiamGia());
        respone.setGiamGiaString(convertUtil.moneyToStringFormat(entity.getGiamGia()));
        respone.setPhaiTraNCC(entity.getTotalMoneyPay());
        respone.setPhaiTraNCCString(convertUtil.moneyToStringFormat(entity.getTotalMoneyPay()));
        respone.setTienThua(entity.getTotalMoneyThua());
        respone.setTienThuaString(convertUtil.moneyToStringFormat(entity.getTotalMoneyThua()));
        respone.setCodeOrder(entity.getCodeOrder());
        respone.setSupplierName(entity.getSupplierEntity().getName());
        respone.setNote(entity.getNote());
        respone.setReceiveDate(String.valueOf(entity.getReceiveDate()).substring(0,10));
        respone.setStatus(entity.getStatus());
        respone.setCreateDate(String.valueOf(entity.getCreateDate()));
        respone.setSupplierId(String.valueOf(entity.getSupplierEntity().getId()));
        respone.setOrderDetail(detailService.findByOrderInvoice(entity.getId()));
        return respone;
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
        entity.setTotalMoneyPay(0L);
        entity.setGiamGia(0L);
        entity.setTotalMoneyThua(0L);
        entity.setTotalMoney(0L);
        entity.setStaffEntity(staffEntity);
        entity.setCreateBy("ADMIN");
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierDate(new Timestamp(System.currentTimeMillis()));
        entity.setModifierBy("ADMIN");
        entity.setDeleteFlag(false);
        return entity;
    }
}
