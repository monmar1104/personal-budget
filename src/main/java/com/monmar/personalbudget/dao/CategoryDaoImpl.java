package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.FinancialTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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
//        String fetchTransactionQ = "select c from Category c join fetch c.financialTransactionList f where c.categoryId=31";
//        String fetchBudgetQ = "select c from Category c join fetch c.budgetList b where c.categoryId=31";
        
//        Query<Category> fetchTransaction = session.createQuery(fetchTransactionQ,Category.class);
//        Category category = fetchTransaction.getSingleResult();
//        Query<Category> fetchBudget = session.createQuery(fetchTransactionQ,Category.class);
//        Category category1 = fetchTransaction.getSingleResult();

        Query<Category> expenditureCategoryQuery = session.createQuery("from Category order by categoryName", Category.class);

        List<Category> categoryList =  expenditureCategoryQuery.getResultList();
        categoryList.size();
//        categoryList.get(0).getBudgetList().size();

        return categoryList;
    }

    @Override
    public Category findCategoryById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Category category = session.get(Category.class, id);


        return category;
    }
}
