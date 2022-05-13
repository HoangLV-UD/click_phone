package com.example.world_phone.dto.respone.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPropertyRespone {
    private long price;
    private String priceString;
    private long quantity;
    private String color;
}
