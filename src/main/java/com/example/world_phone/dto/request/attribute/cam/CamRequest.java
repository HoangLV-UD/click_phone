package com.example.world_phone.dto.request.attribute.cam;

import com.example.world_phone.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamRequest extends BaseEntity {
    private Long id;
    private String nameTruoc;
    private String nameSau;
}