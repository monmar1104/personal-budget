package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.Category;

import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);
    void removeCategory(Category category);
    Category findCategoryByName(String catName);
    List<Category> getCategoryList();
}
