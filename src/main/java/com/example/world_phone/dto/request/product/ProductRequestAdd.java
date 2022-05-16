package com.example.world_phone.dto.request.product;


import com.example.world_phone.dto.request.attribute.AttributeRequestAdd;
import com.example.world_phone.dto.request.rom.RomRequestAdd;
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
public class ProductRequestAdd {

    private String nameProduct;

    private Long categoryId;


    private String note;

    private Date create_Date;

    private String create_By;

    private List<String> image;

    private AttributeRequestAdd attributeRequestAdd;

    private List<RomRequestAdd> romRequestAdds;
}
