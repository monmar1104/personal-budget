package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.ExpenditureCategory;

import java.util.List;

public interface CategoryDao {
    void saveCategory(ExpenditureCategory category);
    void removeCategory(ExpenditureCategory category);
    ExpenditureCategory findCategoryByName(String catName);
    List<ExpenditureCategory> getCategoryList();
}
