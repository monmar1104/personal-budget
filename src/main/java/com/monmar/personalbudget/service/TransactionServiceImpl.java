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
}
