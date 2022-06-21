package com.example.world_phone.dto.request.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeProductRequest {
    private Long id;

    private Integer osId; // he dieu hanh


    private Integer screenId; // man hinh

    private String chip;


    private String camTruoc;


    private String camSau;

    private String sim;

    private String pin;


    private Integer ramID;

    private Long productId;
}
