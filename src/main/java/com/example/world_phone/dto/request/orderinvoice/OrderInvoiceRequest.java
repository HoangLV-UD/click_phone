package com.example.world_phone.dto.request.orderinvoice;

import com.example.world_phone.dto.request.orderinvoicedetail.OrderInvoiceDetailRequest;
import com.example.world_phone.entity.InvoiceOrderDetailEntity;
import com.example.world_phone.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInvoiceRequest {
    private String note;
    private String orderCode;
    private Long totalMoney;
    private String receiveDate;
    private Integer suppliderId;
    private List<OrderInvoiceDetailRequest> detailRequest;
}
