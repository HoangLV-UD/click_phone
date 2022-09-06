package com.example.world_phone.dto.request.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: hieu
 * @since: 04/09/2022
 * Project_name: com.example.world_phone.dto.request.orderdetail
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private String id;
    private String productId;
    private String quantity;
    private String price;
}
