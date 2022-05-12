package com.example.world_phone.dto.respone.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;

    private String nameProduct;

    private String imageProduct;

    private Date create_Date;

    private String create_By;
}
