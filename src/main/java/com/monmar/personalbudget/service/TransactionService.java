package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.FinancialTransaction;

import java.util.List;

public interface TransactionService {
    List<FinancialTransaction> getTransactionList();
}
