package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class BudgetDaoImpl implements BudgetDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBudgetItem(Budget budget, Category category, BigDecimal amount) {

        Session session = sessionFactory.getCurrentSession();

        BudgetDetail budgetDetail = new BudgetDetail();


        session.saveOrUpdate(budget);
        session.saveOrUpdate(category);
        budgetDetail.setBudget(budget);
        budgetDetail.setCategory(category);
        budgetDetail.setBudgetDetailAmount(amount);
        session.saveOrUpdate(budgetDetail);
    }

    @Override
    public List<BudgetDetail> getBudgetDetailList() {

        Session session = sessionFactory.getCurrentSession();

        Query<BudgetDetail> query = session.createQuery("from BudgetDetail", BudgetDetail.class);

        return query.getResultList();
    }
}
