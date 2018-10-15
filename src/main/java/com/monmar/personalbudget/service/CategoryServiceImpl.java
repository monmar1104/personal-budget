package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.CategoryDao;
import com.monmar.personalbudget.entity.ExpenditureCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    @Transactional
    public void saveCategory(ExpenditureCategory category) {

        categoryDao.saveCategory(category);
    }

    @Override
    @Transactional
    public void removeCategory(ExpenditureCategory category) {

    }

    @Override
    @Transactional
    public ExpenditureCategory findCategoryByName(String catName) {
        return null;
    }

    @Override
    @Transactional
    public List<ExpenditureCategory> getCategoryList() {

        List<ExpenditureCategory> categoryList = categoryDao.getCategoryList();

        return categoryList;
    }
}
