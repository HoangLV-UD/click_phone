package com.example.world_phone.service;

import com.example.world_phone.dto.request.orderinvoicedetail.OrderInvoiceDetailRequest;
import com.example.world_phone.entity.InvoiceOrderEntity;

import java.util.List;

public interface IOrderInvoiceDetailService {

    String createOrderInvoiceDetail(List<OrderInvoiceDetailRequest> list, InvoiceOrderEntity entity);
}
