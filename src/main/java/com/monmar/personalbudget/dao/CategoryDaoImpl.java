package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public void removeCategory(Category category) {

    }

    @Override
    public Category findCategoryByName(String catName) {
        return null;
    }

    @Override
    public List<Category> getCategoryList() {

        Session session = sessionFactory.getCurrentSession();

        Query<Category> expenditureCategoryQuery = session.createQuery("from Category order by categoryName", Category.class);

        List<Category> categoryList =  expenditureCategoryQuery.getResultList();
        categoryList.size();
//        int size = categoryList.get(0).getBudgetList().get(0).getBudget().getCategoryList().size();
        categoryList.get(0).getBudgetList().size();

        return categoryList;
    }

    @Override
    public Category findCategoryById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Category category = session.get(Category.class, id);


        return category;
    }
}
