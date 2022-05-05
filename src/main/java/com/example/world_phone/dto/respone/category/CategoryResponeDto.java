package com.example.world_phone.dto.respone.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponeDto {

    private String categoryId;
    private String categoryName;
}
