package com.project.ecommerce.mappers.category;

import com.project.ecommerce.dtos.category.CategoryCreateDTO;
import com.project.ecommerce.dtos.category.CategoryDTO;
import com.project.ecommerce.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    List<CategoryDTO> categoryEntityListToCategoryDTOList(List<CategoryEntity> entities);

    CategoryDTO categoryEntityToCategoryDTO(CategoryEntity entity);

    CategoryEntity categoryDTOToCategoryEntity(CategoryCreateDTO dto);

}
