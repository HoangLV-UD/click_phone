package com.example.world_phone.dto.respone.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private String id;
    private String categoryName;
//    private String parentId;
//    private List<CategoryDTO> categoryChild;
}
