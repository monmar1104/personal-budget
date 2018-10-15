package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.ExpenditureCategory;

import java.util.List;
import java.util.Locale;

public interface CategoryService {

    void saveCategory(ExpenditureCategory category);
    void removeCategory(ExpenditureCategory category);
    ExpenditureCategory findCategoryByName(String catName);
    List<ExpenditureCategory> getCategoryList();
}
