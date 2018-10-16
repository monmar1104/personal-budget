package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.OperationType;

import java.math.BigDecimal;

public interface BudgetService {

    void addBudgetItem(Budget budget, Category category, BigDecimal amount, OperationType operationType);
}
