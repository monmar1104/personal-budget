package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.BudgetDao;
import com.monmar.personalbudget.entity.BudgetDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetDao budgetDao;

    @Override
    @Transactional
    public void addBudgetItem(BudgetDetail budgetDetail) {
        budgetDao.addBudgetItem(budgetDetail);
    }

    @Override
    @Transactional
    public List<BudgetDetail> getBudgetDetailList() {
        return budgetDao.getBudgetDetailList();
    }

    @Override
    @Transactional
    public List<BudgetDetail> getBudgetDetailListByName(String name) {
        return budgetDao.getBudgetDetailListByName(name);
    }

    @Override
    @Transactional
    public List<BudgetDetail> searchBudgetItemByCatName(String name) {
        return budgetDao.searchBudgetItemByCatName(name);
    }

    @Override
    @Transactional
    public BudgetDetail getBudgetDetailById(int id) {
        return budgetDao.getBudgetDetailById(id);
    }

    @Override
    @Transactional
    public void deleteTransactionById(int id) {
        budgetDao.deleteTransactionById(id);
    }

    @Override
    @Transactional
    public Map<Integer, Double> getSumOfTransactionByCategoryMap(int budgetId) {
        return budgetDao.getSumOfTransactionByCategoryMap(budgetId);
    }
}
