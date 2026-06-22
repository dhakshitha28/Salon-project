package com.MD.category.service;

import com.MD.category.Model.Category;
import com.MD.salon.service.payLoad.DTO.SalonDTO;

import java.util.Set;


public interface CategoryService {


    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalonId(Long salonId);
    Category getCategoryById(Long categoryId) throws Exception;
    void deleteCategoryById(Long categoryId,Long salonId) throws Exception;
}
