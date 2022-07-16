package com.example.world_phone.dto.respone.attribute;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeRespone {
    private String id;

    private String operatingSystem; // he dieu hanh

    private String screen; // man hinh

    private String chip;

    private String cam;

    private String pin;

    private String ram;
}
