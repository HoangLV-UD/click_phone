package com.example.world_phone.dto.request.attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeRequestAdd {

    private String operatingSystem; // he dieu hanh

    private String screen; // man hinh

    private String chip;

    private String camTruoc;

    private String camSau;

    private String sim;

    private String pin;

    private String ram;
}
