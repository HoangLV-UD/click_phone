package com.example.world_phone.dto.request.attribute.screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenRequest {
    private Long id;
    private String name;
    private int loai;
}