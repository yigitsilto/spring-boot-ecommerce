package com.project.ecommerce.dtos.category;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CategoryDTO {

    private String name;

    private UUID id;

    private String slug;

    private UUID parentId;

    private List<CategoryDTO> child;

}
