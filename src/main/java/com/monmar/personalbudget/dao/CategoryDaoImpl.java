package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.ExpenditureCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void saveCategory(ExpenditureCategory category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
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

        Session session = sessionFactory.getCurrentSession();

        Query<ExpenditureCategory> expenditureCategoryQuery = session.createQuery("from ExpenditureCategory order by categoryName", ExpenditureCategory.class);

        return expenditureCategoryQuery.getResultList();
    }
}
