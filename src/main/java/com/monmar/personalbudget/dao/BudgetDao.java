package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;

import java.math.BigDecimal;

public interface BudgetDao {

    void addBudgetItem(Budget budget, Category category, BigDecimal amount);
}
