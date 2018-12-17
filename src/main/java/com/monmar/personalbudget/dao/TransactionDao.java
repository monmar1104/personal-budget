package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.entity.Stat;

import java.time.LocalDate;
import java.util.List;

public interface TransactionDao {
    List<FinancialTransaction> getTransactionList(int userId);

    void saveTransaction(FinancialTransaction financialTransaction);

    List<FinancialTransaction> searchTransactionByNameByUserId(String name, int userId);

    FinancialTransaction getTransactionById(int id);

    void deleteTransactionById(int id);

    List<FinancialTransaction> searchTransactionByDateByUserId(String dateFrom, String dateTo, int userId);

	List<FinancialTransaction> searchTransactionByIdByUserIdByDate(int categoryId, LocalDate budgetDateFrom,
			LocalDate budgetDateTo, int userId);

	List<Stat> getSumOfTransactionsByCategoryByDate(int userId, int categoryId, String dateFrom,
			String dateTo);
}
