package com.project.ecommerce.services.category;

import com.project.ecommerce.dtos.category.CategoryCreateDTO;
import com.project.ecommerce.dtos.category.CategoryDTO;
import com.project.ecommerce.entities.CategoryEntity;
import com.project.ecommerce.mappers.category.CategoryMapper;
import com.project.ecommerce.repositories.CategoryRepository;
import com.project.ecommerce.utils.SlugConverterUtil;
import com.project.ecommerce.validators.CategoryBaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    private final CategoryBaseValidator categoryBaseValidator;

    @Override
    public List<CategoryDTO> findAll() {

        return mapper.categoryEntityListToCategoryDTOList(repository.findAll());
    }

    @Override
    public CategoryDTO findById(UUID id){
        return mapper.categoryEntityToCategoryDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("not.exists")));
    }

    @Override
    public CategoryDTO create(CategoryCreateDTO createDTO) {
        String slug = SlugConverterUtil.convertToSlug(createDTO.getName());
        categoryBaseValidator.checkExistsBySlug(slug); // validation for category slugs

        CategoryEntity categoryEntity = mapper.categoryDTOToCategoryEntity(createDTO);
        categoryEntity.setSlug(slug);

        return mapper.categoryEntityToCategoryDTO(repository.saveAndFlush(categoryEntity));
    }

    @Override
    public CategoryDTO update(UUID id, CategoryCreateDTO createDTO) {
        checkExistsById(id); // exists control

        CategoryEntity categoryEntity = mapper.categoryDTOToCategoryEntity(createDTO);
        categoryEntity.setId(id);

        return mapper.categoryEntityToCategoryDTO(repository.saveAndFlush(categoryEntity));
    }

    protected void checkExistsById(UUID id){
        if (!repository.existsById(id)){
            throw new RuntimeException("not.exists");
        }
    }


}
