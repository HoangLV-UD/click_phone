package com.example.world_phone.dto.request.order;

import com.example.world_phone.dto.request.orderdetail.OrderDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 04/09/2022
 * Project_name: com.example.world_phone.dto.request.order
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String id;
    private String totalMoney;
    private List<OrderDetailRequest> detailRequest;
    private String recipientAddress; //địa chỉ giao hàng
    private Date deliveryDate; // ngày giao hàng
}
