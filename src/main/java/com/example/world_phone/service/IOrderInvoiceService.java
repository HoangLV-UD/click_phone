package com.example.world_phone.service;

import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;

public interface IOrderInvoiceService {

    String createOrderInvoice(OrderInvoiceRequest request);
}
