package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.entity.Stat;

import exception.EmptyArgumentException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NamedNativeQuery;
import javax.persistence.Tuple;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<FinancialTransaction> getTransactionList(int userId) {

		Session session = sessionFactory.getCurrentSession();

		Query<FinancialTransaction> query = session.createQuery(
				"from FinancialTransaction f where f.transactionUser.id=:userId order by f.transactionDate desc",
				FinancialTransaction.class);

		return query.setParameter("userId", userId).getResultList();
	}

	@Override
	public void saveTransaction(FinancialTransaction financialTransaction) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(financialTransaction);
	}

	@Override
	public List<FinancialTransaction> searchTransactionByNameByUserId(String name, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<FinancialTransaction> query = null;
		List<FinancialTransaction> transactionList = null;

		String hql = "from FinancialTransaction f where f.category.categoryName like :name and f.transactionUser.id=:userId order by f.transactionDate desc";

		if (name != null && name.trim().length() > 0) {
			query = session.createQuery(hql, FinancialTransaction.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
			query.setParameter("userId", userId);
			transactionList = query.getResultList();

		} else {
			transactionList = new ArrayList<>();
		}
		return transactionList;
	}

	@Override
	public List<FinancialTransaction> searchTransactionByDateByUserId(String dateFrom, String dateTo, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<FinancialTransaction> query = null;
		List<FinancialTransaction> financialTransactionList = null;

		if (dateFrom != "" && dateTo != "") {

			LocalDate localDateFrom = LocalDate.of(Integer.parseInt(dateFrom.split("-")[0]),
					Integer.parseInt(dateFrom.split("-")[1]), Integer.parseInt(dateFrom.split("-")[2]));
			LocalDate localDateTo = LocalDate.of(Integer.parseInt(dateTo.split("-")[0]),
					Integer.parseInt(dateTo.split("-")[1]), Integer.parseInt(dateTo.split("-")[2]));

			String hql = "from FinancialTransaction f where f.transactionDate between :dateFrom and :dateTo and f.transactionUser.id=:userId order by f.transactionDate desc";
			query = session.createQuery(hql, FinancialTransaction.class);
			query.setParameter("dateFrom", localDateFrom).setParameter("dateTo", localDateTo).setParameter("userId",
					userId);
			financialTransactionList = query.setParameter("userId", userId).getResultList();
		} else {
			query = session.createQuery(
					"from FinancialTransaction where f.transactionUser.id=:userId order by f.transactionDate desc",
					FinancialTransaction.class);

			financialTransactionList = query.setParameter("userId", userId).getResultList();
		}

		return financialTransactionList;
	}

	@Override
	public FinancialTransaction getTransactionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(FinancialTransaction.class, id);
	}

	@Override
	public void deleteTransactionById(int id) {
		Session session = sessionFactory.getCurrentSession();

		session.delete(getTransactionById(id));

	}

	@Override
	public List<FinancialTransaction> searchTransactionByIdByUserIdByDate(int categoryId,
			LocalDate budgetDateFrom, LocalDate budgetDateTo, int userId) {

		Session session = sessionFactory.getCurrentSession();
		Query<FinancialTransaction> query = null;
		List<FinancialTransaction> financialTransactionList = null;

		String hql = "from FinancialTransaction f  where f.category.categoryId=:categoryId and f.transactionDate between :dateFrom and :dateTo and f.transactionUser.id=:userId order by f.transactionDate desc";
		query = session.createQuery(hql, FinancialTransaction.class);
		query.setParameter("categoryId", categoryId).setParameter("dateFrom", budgetDateFrom).setParameter("dateTo", budgetDateTo).setParameter("userId",
				userId);
		financialTransactionList = query.setParameter("userId", userId).getResultList();

		return financialTransactionList;
	}

	@Override
	public List<Stat> getSumOfTransactionsByCategoryByDate(int userId, int categoryId, String dateFrom,
			String dateTo) {
		Session session = sessionFactory.getCurrentSession();
		
		String[] dateFromA = dateFrom.split("-");
		String[] dateToA = dateTo.split("-");
		
		String hql = "select new com.monmar.personalbudget.entity.Stat(f.category.categoryName, DATE_FORMAT(f.transactionDate,'%M,%Y'), sum(f.transactionAmount)) "
				+ "from FinancialTransaction f  "
				+ "where f.category.categoryId=:categoryId and f.transactionDate between :dateFrom and :dateTo and f.transactionUser.id=:userId "
				+ "group by f.category.categoryName, DATE_FORMAT(f.transactionDate,'%M,%Y')";

		
		List<Stat> stats = session.createQuery(hql)
				.setParameter("categoryId", categoryId)
				.setParameter("dateFrom", LocalDate.of(Integer.valueOf(dateFromA[0]), Integer.valueOf(dateFromA[1]), Integer.valueOf(dateFromA[2])))
				.setParameter("dateTo", LocalDate.of(Integer.valueOf(dateToA[0]), Integer.valueOf(dateToA[1]), Integer.valueOf(dateToA[2])))
				.setParameter("userId", userId).setParameter("userId", userId)
				.getResultList();
		

		return stats;

	}

}
