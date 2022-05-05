package com.example.world_phone.dto.request.category;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryReqDto {
    private String categoryId;
    private String categoryName;
}
