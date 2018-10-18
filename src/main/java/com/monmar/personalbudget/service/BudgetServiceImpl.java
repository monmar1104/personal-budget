package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.BudgetDao;
import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetDao budgetDao;

    @Override
    @Transactional
    public void addBudgetItem(Budget budget, Category category, BigDecimal amount) {
        budgetDao.addBudgetItem(budget, category, amount);
    }

    @Override
    @Transactional
    public List<BudgetDetail> getBudgetDetailList() {
        return budgetDao.getBudgetDetailList();
    }
}
