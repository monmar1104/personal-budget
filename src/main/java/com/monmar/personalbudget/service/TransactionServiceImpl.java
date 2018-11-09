package com.monmar.personalbudget.service;

import com.monmar.personalbudget.dao.TransactionDao;
import com.monmar.personalbudget.entity.FinancialTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Override
    @Transactional
    public List<FinancialTransaction> getTransactionListByUserId(int userId) {
        return transactionDao.getTransactionList(userId);
    }

    @Override
    @Transactional
    public void saveTransaction(FinancialTransaction financialTransaction) {
        transactionDao.saveTransaction(financialTransaction);
    }

    @Override
    @Transactional
    public List<FinancialTransaction> searchTransactionByNameByUserId(String name, int userId) {
        return transactionDao.searchTransactionByNameByUserId(name, userId);
    }

    @Override
    @Transactional
    public FinancialTransaction getTransactionById(int id) {
        return transactionDao.getTransactionById(id);
    }

    @Override
    @Transactional
    public void deleteTransactionById(int id) {

        transactionDao.deleteTransactionById(id);

    }

    @Override
    @Transactional
    public List<FinancialTransaction> searchTransactionByDateByUserId(String dateFrom, String dateTo, int userId) {
        return transactionDao.searchTransactionByDateByUserId(dateFrom, dateTo, userId);
    }

}
