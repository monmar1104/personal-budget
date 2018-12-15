package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.BudgetDao;
import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;

import exception.EmptyArgumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetDao budgetDao;

    @Override
    @Transactional
    public void addBudgetItem(BudgetDetail budgetDetail) {
    	if(budgetDetail.getBudget()==null) {
    		throw new EmptyArgumentException("You must create budget, before adding budget item!");
    	}
        budgetDao.addBudgetItem(budgetDetail);
    }

    @Override
    @Transactional
    public List<BudgetDetail> getBudgetDetailList() {
        return budgetDao.getBudgetDetailList();
    }

    @Override
    @Transactional
    public List<BudgetDetail> searchBudgetItemByCatName(String name, int budgetId) {
        return budgetDao.searchBudgetItemByCatName(name, budgetId);
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

    @Override
    @Transactional
    public List<Budget> getBudgetList(int userId) {
        return budgetDao.getBudgetList(userId);
    }

    @Override
    @Transactional
    public Budget getLastBudget(int userId){
        return budgetDao.getLastBudget(userId);
    }

    @Override
    @Transactional
    public List<BudgetDetail> getBudgetDetailListByBudgetId(int budgetId) {
        return budgetDao.getBudgetDetailListByBudgetId(budgetId);
    }

    @Override
    @Transactional
    public Budget getBudgetById(int budgetId) {
        return budgetDao.getBudgetById(budgetId);
    }
    
    @Override
    @Transactional
    public void addBudget(Budget budget, int budgetId) {
    	budget.setBudgetCreationDate(LocalDate.now());
    	budgetDao.addBudget(budget);
    	
    	if(budgetId > 0) {
    		for(BudgetDetail bd:getBudgetDetailListByBudgetId(budgetId)) {
    			
    			addBudgetItem(new BudgetDetail(0, bd.getBudgetDetailAmount(),bd.getBudgetDetailDescription(),budget,bd.getCategory()));
    		}
    	}
    	
    }

	@Override
	public Budget getCurrentBudget(int userId) {
		return budgetDao.getCurrentBudget(userId);
	}
}
