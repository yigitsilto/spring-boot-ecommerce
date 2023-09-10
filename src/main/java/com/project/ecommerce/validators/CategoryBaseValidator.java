package com.project.ecommerce.validators;

import com.project.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryBaseValidator implements BaseValidator{

    private final CategoryRepository repository;

    @Override
    public void checkExistsBySlug(String slug) {
        boolean existBySlug = repository.existsBySlug(slug);
        if (existBySlug) {
            throw new RuntimeException("exists by slug");
        }
    }
}
