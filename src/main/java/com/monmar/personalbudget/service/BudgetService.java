package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;

import java.math.BigDecimal;

public interface BudgetService {

    void addBudgetItem(Budget budget, Category category, BigDecimal amount);
}
