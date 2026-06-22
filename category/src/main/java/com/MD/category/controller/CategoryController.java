package com.MD.category.controller;

import com.MD.category.Model.Category;
import com.MD.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<Category>>getCategoriesBySalon(
            @PathVariable Long salonId
    )
    {
        Set<Category> categories= categoryService.getAllCategoriesBySalonId(salonId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<Category>getCategoriesBySalonId(
            @PathVariable Long salonId
    ) throws Exception {
        Category category= categoryService.getCategoryById(salonId);
        return ResponseEntity.ok(category);
    }
}
