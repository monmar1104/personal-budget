package com.monmar.personalbudget.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;

@Repository
public class BudgetDaoImpl implements BudgetDao {

	Logger logger = Logger.getLogger(BudgetDaoImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBudgetItem(BudgetDetail budgetDetail) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(budgetDetail);
	}

	@Override
	public List<BudgetDetail> getBudgetDetailList() {

		Session session = sessionFactory.getCurrentSession();

		Query<BudgetDetail> query = session.createQuery("from BudgetDetail bd order by bd.category.categoryName", BudgetDetail.class);

		return query.getResultList();
	}

	@Override
	public List<BudgetDetail> getBudgetDetailListByBudgetId(int budgetId) {

		logger.info("===============>>>>>>Enter to method getBudgetDetailListByBudgetId(int budgetId) with id: "
				+ budgetId);
		List<BudgetDetail> budgetDetails = null;

		Session session = sessionFactory.getCurrentSession();

		Query<BudgetDetail> query = session
				.createQuery("from BudgetDetail bd where bd.budget.budgetId=:id order by bd.category.categoryName", BudgetDetail.class)
				.setParameter("id", budgetId);

		if (budgetId > 0) {
			budgetDetails = query.getResultList();
			logger.info("================>>>>>End of method getBudgetDetailListByBudgetId(int budgetId) - size of list: "
					+ budgetDetails.size());
		}
		else {
			budgetDetails = new ArrayList<>();
			logger.info("================>>>>>End of method getBudgetDetailListByBudgetId(int budgetId) - no Data ");
		}

		return budgetDetails;
	}

//	@Override
//	public List<BudgetDetail> getBudgetDetailListByName(String name) {
//		Session session = sessionFactory.getCurrentSession();
//		int id = getBudgetIdByname(name);
//		Query<BudgetDetail> query = session
//				.createQuery("from BudgetDetail bd where bd.budget.budgetId=:budgetId order by bd.category.categoryName", BudgetDetail.class)
//				.setParameter("budgetId", id);
//
//		return query.getResultList();
//	}

	private int getBudgetIdByname(String name) {
		Session session = sessionFactory.getCurrentSession();
		Budget budget = session.createQuery("from Budget b where b.budgetName=:budgetName", Budget.class)
				.setParameter("budgetName", name).getSingleResult();
		return budget.getBudgetId();
	}

	public Budget getBudgetById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Budget.class, id);
	}

	@Override
	public List<BudgetDetail> searchBudgetItemByCatName(String name, int budgetId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		String hql = "from BudgetDetail bd where bd.category.categoryName like :name and bd.budget.budgetId=:budgetId order by bd.category.categoryName";

		if (name != null && name.trim().length() > 0) {
			query = session.createQuery(hql, BudgetDetail.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
			

		} else {
			query = session.createQuery("from BudgetDetail bd where bd.budget.budgetId=:budgetId order by bd.category.categoryName", BudgetDetail.class);
		}
		query.setParameter("budgetId", budgetId);

		List<BudgetDetail> budgetDetailList = query.getResultList();

		return budgetDetailList;
	}

	@Override
	public BudgetDetail getBudgetDetailById(int id) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(BudgetDetail.class, id);
	}

	@Override
	public void deleteTransactionById(int id) {
		Session session = sessionFactory.getCurrentSession();

		session.delete(getBudgetDetailById(id));
	}

	@Override
	public Map<Integer, Double> getSumOfTransactionByCategoryMap(int budgetId) {
		
		if(budgetId == 0) {
			return new HashMap<Integer, Double>(){{put(0,0.0);}};
		}
		
		Session session = sessionFactory.getCurrentSession();

		String hql = "select t.category.categoryId, sum(t.transactionAmount) " + "from FinancialTransaction t "
				+ "where t.transactionDate between :dateFrom and :dateTo " + " and t.transactionUser.id=:userId " + "group by t.category.categoryId";

		Budget budget = getBudgetById(budgetId);
		
		int userId = budget.getBudgetUser().getId();

		LocalDate dateFrom = budget.getBudgetDateFrom();
		LocalDate dateTo = budget.getBudgetDateTo();

		List<Object[]> objectList = session.createQuery(hql).setParameter("dateFrom", dateFrom)
				.setParameter("dateTo", dateTo).setParameter("userId", userId).list();

		Map<Integer, Double> sumOfCategoryMap = new HashMap<>();
		for (Object[] i : objectList) {
			sumOfCategoryMap.put((Integer) i[0], (Double) i[1]);

		}
		return sumOfCategoryMap;
	}

	@Override
	public List<Budget> getBudgetList(int userId) {
		Session session = sessionFactory.getCurrentSession();
		List<Budget> budgetList = session.createQuery("from Budget where budgetUser.id=:userId")
				.setParameter("userId", userId).getResultList();

		return budgetList;
	}

	@Override
	public Budget getLastBudget(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Budget budget = null;
		Query<Budget> budgetQuery = session.createQuery(
				"from Budget b where budgetUser.id=:userId and b.budgetCreationDate = (select max(budget.budgetCreationDate) as maxDate from Budget budget where budgetUser.id=:userId)",
				Budget.class).setParameter("userId", userId);
		
		if (budgetQuery.list().size()>0) {
			budget = budgetQuery.list().get(0);
//			budget.getBudgetDetailList().size();
		} else {
			budget = new Budget();
			logger.info("==========>>>>>> getLastBudget() No data");
		}
		
		return budget;
	}
	
	@Override
	public void addBudget(Budget budget) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(budget);
	}
}
