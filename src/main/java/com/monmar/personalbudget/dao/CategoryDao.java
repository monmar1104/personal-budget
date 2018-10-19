package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Category;

import java.util.List;

public interface CategoryDao {
    void saveCategory(Category category);
    void removeCategory(Category category);
    Category findCategoryByName(String catName);
    List<Category> getCategoryList();
    Category findCategoryById(int id);

}
