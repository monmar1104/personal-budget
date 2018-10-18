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

        return expenditureCategoryQuery.getResultList();
    }
}
