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

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    private final CategoryBaseValidator categoryBaseValidator;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> categoryEntities = repository.findAll();
        return buildChildsMap(categoryEntities);
    }


    /**
     * this function build parent list and the childs by key value with map
     * @param categoryEntities all categories from database
     * @return list of converted category dto
     */
    protected List<CategoryDTO>  buildChildsMap(List<CategoryEntity> categoryEntities){
        Map<UUID, List<CategoryDTO>> childMap = new HashMap<>();

        categoryEntities.forEach(e -> {
            if (e.getParentId() != null) {
                CategoryDTO childDTO = mapper.categoryEntityToCategoryDTO(e);
                childMap.computeIfAbsent(e.getParentId(), k -> new ArrayList<>()).add(childDTO);
            }
        });

        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categoryEntities.forEach(e -> {
            if (e.getParentId() == null) {
                CategoryDTO parentDTO = mapper.categoryEntityToCategoryDTO(e);
                populateChildren(parentDTO, childMap);
                categoryDTOList.add(parentDTO);
            }
        });

        return categoryDTOList;

    }

    /**
     * add childs to parent with recursive
     * @param parentDTO
     * @param childMap
     */
    protected void populateChildren(CategoryDTO parentDTO, Map<UUID, List<CategoryDTO>> childMap) {
        List<CategoryDTO> children = childMap.get(parentDTO.getId());
        if (children != null) {
            parentDTO.setChild(children);
            children.forEach(child -> populateChildren(child, childMap));
        }
    }


    @Override
    public CategoryDTO findById(UUID id) {
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

    protected void checkExistsById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("not.exists");
        }
    }


}
