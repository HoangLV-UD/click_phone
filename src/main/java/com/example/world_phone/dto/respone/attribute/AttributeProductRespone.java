package com.example.world_phone.dto.respone.attribute;

import com.example.world_phone.dto.respone.attribute.os.OsRespone;
import com.example.world_phone.dto.respone.attribute.ram.RamRespone;
import com.example.world_phone.dto.respone.attribute.screen.ScreenReposne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeProductRespone {
    private Long id;

    private OsRespone osId; // he dieu hanh


    private ScreenReposne screenId; // man hinh

    private String chip;


    private String camTruoc;


    private String camSau;

    private String sim;

    private String pin;

    private RamRespone ramID;


}
