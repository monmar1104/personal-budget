package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.OperationType;

import java.math.BigDecimal;

public interface BudgetDao {

    void addBudgetItem(Budget budget, Category category, BigDecimal amount, OperationType operationType);
}
