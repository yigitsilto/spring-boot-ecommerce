package com.project.ecommerce.services.category;

import com.project.ecommerce.dtos.category.CategoryCreateDTO;
import com.project.ecommerce.dtos.category.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO findById(UUID id);

    CategoryDTO create(@RequestBody @Valid CategoryCreateDTO createDTO);


    CategoryDTO update(UUID id, CategoryCreateDTO createDTO);
}
