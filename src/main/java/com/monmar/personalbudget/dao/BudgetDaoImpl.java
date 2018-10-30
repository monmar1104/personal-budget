package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.FinancialTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BudgetDaoImpl implements BudgetDao {

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

        Query<BudgetDetail> query = session.createQuery("from BudgetDetail", BudgetDetail.class);

        return query.getResultList();
    }

    @Override
    public List<BudgetDetail> getBudgetDetailListByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        int id = getBudgetIdByname(name);
        Query<BudgetDetail> query = session.createQuery("from BudgetDetail where budget.budgetId=:budgetId", BudgetDetail.class).setParameter("budgetId", id);

        return query.getResultList();
    }

    private int getBudgetIdByname(String name) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select b.budgetId from BudgetDetail b where b.budgetName=:budgetName", BudgetDetail.class).setParameter("budgetName", name).getFirstResult();
    }

    private Budget getBudgetById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Budget b where b.budgetId=:budgetId", Budget.class).setParameter("budgetId", id).getSingleResult();
    }

    @Override
    public List<BudgetDetail> searchBudgetItemByCatName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        String hql = "from BudgetDetail b where b.category.categoryName like :name";

        if (name != null && name.trim().length() > 0) {
            query = session.createQuery(hql,
                    BudgetDetail.class);
            query.setParameter("name", "%" + name.toLowerCase() + "%");

        } else {
            query = session.createQuery("from BudgetDetail", BudgetDetail.class);
        }

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
        Session session = sessionFactory.getCurrentSession();

        String hql = "select t.category.categoryId, sum(t.transactionAmount) " +
                "from FinancialTransaction t " +
                "where t.transactionDate between :dateFrom and :dateTo " +
                "group by t.category.categoryId";

        Budget budget = getBudgetById(budgetId);

        LocalDate dateFrom = budget.getBudgetDateFrom();
        LocalDate dateTo = budget.getBudgetDateTo();

         List<Object[]> objectList= session.createQuery(hql).setParameter("dateFrom", dateFrom).setParameter("dateTo", dateTo).list();

        Map<Integer, Double> sumOfCategoryMap = new HashMap<>();
        for (Object[] i : objectList) {
            sumOfCategoryMap.put((Integer) i[0], (Double) i[1]);

        }
        return sumOfCategoryMap;
    }
}
