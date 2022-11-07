package com.example.world_phone.dto.respone.product;

import com.example.world_phone.dto.respone.imei.ImeiResponse;
import com.example.world_phone.dto.respone.rom.RomRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPropertyRespone {
    private String id;
    private String romId;
    private String colorId;
    private long price;
    private String priceString;
    private long quantity;
    private String colorName;
    private String status;
    private String pricePromotionString;
    private long pricePromotion;
    private long countImei;
    private List<ImeiResponse> imeiResponses;
}
