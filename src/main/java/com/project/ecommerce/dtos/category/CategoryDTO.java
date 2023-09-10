package com.project.ecommerce.dtos.category;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDTO {

    private String name;

    private UUID id;

    private String slug;

    private UUID parentId;

}
