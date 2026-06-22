package com.MD.category.service.Impl;

import com.MD.category.Model.Category;
import com.MD.category.repository.CategoryRepository;
import com.MD.category.service.CategoryService;
import com.MD.salon.service.payLoad.DTO.SalonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {

        Category newCategory =new Category();
        newCategory.setName(category.getName());
        newCategory.setSalonId(salonDTO.getId());
        newCategory.setImage(category.getImage());

        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {
        return categoryRepository.findBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new Exception("Category not found with id: " + categoryId);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long categoryId,Long salonId) throws Exception {

        Category category=getCategoryById(categoryId);

        if(!category.getSalonId().equals(salonId)){
            throw new Exception("You do not have permission to delete this category");
        }
        categoryRepository.deleteById(categoryId);
    }
}
