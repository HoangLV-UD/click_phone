package com.example.world_phone.dto.respone.product;

import com.example.world_phone.dto.respone.attribute.AttributeRespone;
import com.example.world_phone.dto.respone.rom.RomRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

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

    private List<String> image;

    private AttributeRespone attributeRespone;

    private List<RomRespone> romRespones;
}
