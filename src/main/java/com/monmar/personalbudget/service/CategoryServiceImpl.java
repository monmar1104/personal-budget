package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.CategoryDao;
import com.monmar.personalbudget.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional
    public void saveCategory(Category category) {

        categoryDao.saveCategory(category);
    }

    @Override
    @Transactional
    public void removeCategory(Category category) {

    }

    @Override
    @Transactional
    public Category findCategoryByName(String catName) {
        return null;
    }

    @Override
    @Transactional
    public List<Category> getCategoryList() {

        List<Category> categoryList = categoryDao.getCategoryList();

        return categoryList;
    }

    @Override
    @Transactional
    public Category findCategoryById(int id) {
        return categoryDao.findCategoryById(id);
    }
}
