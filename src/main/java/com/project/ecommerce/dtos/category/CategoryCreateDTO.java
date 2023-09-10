package com.project.ecommerce.dtos.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CategoryCreateDTO {

    @NotBlank
    private String name;

    private UUID parentId;

    @NotNull
    private boolean isActive;

}
