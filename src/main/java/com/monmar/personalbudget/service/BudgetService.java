package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;

import java.util.List;
import java.util.Map;

public interface BudgetService {

    void addBudgetItem(BudgetDetail budgetDetail);

    List<BudgetDetail> getBudgetDetailList();

    List<BudgetDetail> searchBudgetItemByCatName(String name, int budgetId);

    BudgetDetail getBudgetDetailById(int id);

    void deleteTransactionById(int id);

    Map<Integer, Double> getSumOfTransactionByCategoryMap(int budgetId);

    List<Budget> getBudgetList(int userId);

    Budget getLastBudget(int userId);

    List<BudgetDetail> getBudgetDetailListByBudgetId(int budgetId);

    Budget getBudgetById(int budgetId);
    
    void addBudget(Budget budget, int budgetId);
    
    Budget getCurrentBudget(int userId);
}
