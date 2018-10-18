package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.FinancialTransaction;

import java.util.List;

public interface TransactionDao {
    List<FinancialTransaction> getTransactionList();

    void saveTransaction(FinancialTransaction financialTransaction);
}
