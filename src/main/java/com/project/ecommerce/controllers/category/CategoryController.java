package com.project.ecommerce.controllers.category;

import com.project.ecommerce.dtos.category.CategoryCreateDTO;
import com.project.ecommerce.dtos.category.CategoryDTO;
import com.project.ecommerce.entities.CategoryEntity;
import com.project.ecommerce.repositories.CategoryRepository;
import com.project.ecommerce.repositories.UserRepository;
import com.project.ecommerce.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryCreateDTO createDTO){
        return ResponseEntity.ok(service.create(createDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CategoryCreateDTO createDTO){
        service.update(id, createDTO);
        return ResponseEntity.ok(null);
    }
}
