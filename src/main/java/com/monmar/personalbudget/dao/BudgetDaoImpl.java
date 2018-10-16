package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.OperationType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class BudgetDaoImpl implements BudgetDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBudgetItem(Budget budget, Category category, BigDecimal amount, OperationType operationType) {

        Session session = sessionFactory.getCurrentSession();

        BudgetDetail budgetDetail = new BudgetDetail();
        
        session.saveOrUpdate(budget);
        session.saveOrUpdate(category);
        budgetDetail.setBudget(budget);
        budgetDetail.setCategory(category);
        budgetDetail.setBudgetDetailAmount(amount);
        budgetDetail.setBudgetDetailOperationType(operationType);
        session.saveOrUpdate(budgetDetail);
    }
}
