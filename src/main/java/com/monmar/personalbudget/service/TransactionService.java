package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.entity.Stat;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<FinancialTransaction> getTransactionListByUserId(int userId);

    void saveTransaction(FinancialTransaction financialTransaction);

    List<FinancialTransaction> searchTransactionByNameByUserId(String name, int userId);

    FinancialTransaction getTransactionById(int id);

    void deleteTransactionById(int id);

    List<FinancialTransaction> searchTransactionByDateByUserId(String dateFrom, String dateTo, int userId);

	List<FinancialTransaction> searchTransactionByIdByUserIdByDate(int categoryId, LocalDate budgetDateFrom,
			LocalDate budgetDateTo, int userId);

	List<Stat> getSumOfTransactionsByCategoryByDate(int id, int categoryId, String dateFrom,
			String dateTo);
}
