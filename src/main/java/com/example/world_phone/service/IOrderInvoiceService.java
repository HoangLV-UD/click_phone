package com.example.world_phone.service;

import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.dto.respone.order_invoice.OrderInvoiceRespone;

import java.util.List;

public interface IOrderInvoiceService {

    String createOrderInvoice(OrderInvoiceRequest request);

    String createNhap(OrderInvoiceRequest request);

    String updateNhap(OrderInvoiceRequest request);

    String updateOrderInvoice(OrderInvoiceRequest request);

    String findByCodeOrder(String code);

    List<OrderInvoiceRespone> finAll();

    List<OrderInvoiceRespone> finAllTotalMoney();

    String changeStatus(Long id, String message);

    OrderInvoiceRespone findById(Long id);
    OrderInvoiceRespone duyetDon(Long id);
}
