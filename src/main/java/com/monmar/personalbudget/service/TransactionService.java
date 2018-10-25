package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.FinancialTransaction;

import java.util.List;

public interface TransactionService {
    List<FinancialTransaction> getTransactionList();

    void saveTransaction(FinancialTransaction financialTransaction);

    List<FinancialTransaction> searchTransactionByName(String name);

    FinancialTransaction getTransactionById(int id);

    void deleteTransactionById(int id);

    List<FinancialTransaction> searchTransactionByDate(String dateFrom, String dateTo);
}
