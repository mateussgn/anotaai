package com.mateussgn.anota_ai.controllers;

import com.mateussgn.anota_ai.domain.category.Category;
import com.mateussgn.anota_ai.domain.category.CategoryDTO;
import com.mateussgn.anota_ai.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service, CategoryService categoryService) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryDTO) {
        Category newCategory = this.service.insert(categoryDTO);

        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = this.service.getAll();

        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, CategoryDTO categoryDTO) {
        Category updatedCategory = this.service.update(id, categoryDTO);

        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable String id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
