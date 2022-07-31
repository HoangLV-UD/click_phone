package com.example.world_phone.dto.request.promotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 29/07/2022
 * Project_name: com.example.world_phone.dto.request.promotion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromotionRequestDTO {
    private String id;

    private String name;

    private Long discount;

    private Date startDate;

    private Date endDate;

    private String description;

    private String typeDiscount;

    private Boolean status;

    private String note;

    private List<String> productIds;

}