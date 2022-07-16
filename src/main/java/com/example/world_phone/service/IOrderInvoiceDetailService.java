package com.example.world_phone.service;

import com.example.world_phone.dto.request.orderinvoicedetail.OrderInvoiceDetailRequest;
import com.example.world_phone.dto.respone.order_invoice.detail.OrderInvoiceDetailRespone;
import com.example.world_phone.entity.InvoiceOrderDetailEntity;
import com.example.world_phone.entity.InvoiceOrderEntity;

import java.util.List;

public interface IOrderInvoiceDetailService {

    String createOrderInvoiceDetail(List<OrderInvoiceDetailRequest> list, InvoiceOrderEntity entity);

    String updateOrderInvoiceDetail(List<OrderInvoiceDetailRequest> list, InvoiceOrderEntity entity);

    List<OrderInvoiceDetailRespone> findByOrderInvoice(Long id);

    String createNhapDetail(List<OrderInvoiceDetailRequest> list, InvoiceOrderEntity entity);
    String updateNhapDetail(List<OrderInvoiceDetailRequest> list, InvoiceOrderEntity entity);
}
