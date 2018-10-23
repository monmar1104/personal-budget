package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.TransactionDao;
import com.monmar.personalbudget.entity.FinancialTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Override
    @Transactional
    public List<FinancialTransaction> getTransactionList() {
        return transactionDao.getTransactionList();
    }

    @Override
    @Transactional
    public void saveTransaction(FinancialTransaction financialTransaction) {
        transactionDao.saveTransaction(financialTransaction);
    }

    @Override
    @Transactional
    public List<FinancialTransaction> searchTransactionByName(String name) {
        return transactionDao.searchTransactionByName(name);
    }

    @Override
    public FinancialTransaction getTransactionById(int id) {
        return transactionDao.getTransactionById(id);
    }

    @Override
    public void deleteTransactionById(int id) {

        transactionDao.deleteTransactionById(id);

    }

}
