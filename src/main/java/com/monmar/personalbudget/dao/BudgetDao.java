package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;

import java.util.List;
import java.util.Map;

public interface BudgetDao {

    void addBudgetItem(BudgetDetail budgetDetail);

    List<BudgetDetail> getBudgetDetailList();

    List<BudgetDetail> getBudgetDetailListByName(String name);

    List<BudgetDetail> searchBudgetItemByCatName(String name);

    BudgetDetail getBudgetDetailById(int id);

    void deleteTransactionById(int id);

    Map<Integer, Double> getSumOfTransactionByCategoryMap(int budgetId);

    List<Budget> getBudgetList(int userId);

    Budget getLastBudget(int userId);

    List<BudgetDetail> getBudgetDetailListByBudgetId(int budgetId);

    Budget getBudgetById(int budgetId);
}
