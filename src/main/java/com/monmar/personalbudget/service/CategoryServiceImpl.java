package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.CategoryDao;
import com.monmar.personalbudget.entity.ExpenditureCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void saveCategory(ExpenditureCategory category) {

    }

    @Override
    public void removeCategory(ExpenditureCategory category) {

    }

    @Override
    public ExpenditureCategory findCategoryByName(String catName) {
        return null;
    }

    @Override
    public List<ExpenditureCategory> getCategoryList() {

        List<ExpenditureCategory> categoryList = categoryDao.getCategoryList();

        return categoryList;
    }
}
