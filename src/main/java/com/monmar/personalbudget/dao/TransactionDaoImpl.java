package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.FinancialTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<FinancialTransaction> getTransactionList(int userId) {

        Session session = sessionFactory.getCurrentSession();

        Query<FinancialTransaction> query = session.createQuery("from FinancialTransaction f where f.transactionUser.id=:userId", FinancialTransaction.class);

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
          String hql = "from FinancialTransaction f where f.category.categoryName like :name and f.transactionUser.id=:userId";

        if (name != null && name.trim().length() > 0) {
            query = session.createQuery(hql,
                    FinancialTransaction.class);
            query.setParameter("name", "%" + name.toLowerCase() + "%");
            query.setParameter("userId", userId);

        } else {
            query = session.createQuery("from FinancialTransaction", FinancialTransaction.class);
        }

        List<FinancialTransaction> transactionList = query.getResultList();

        return transactionList;
    }

    @Override
    public List<FinancialTransaction> searchTransactionByDateByUserId(String dateFrom, String dateTo, int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<FinancialTransaction> query = null;

        if(dateFrom != "" && dateTo != "") {

            LocalDate localDateFrom = LocalDate.of(Integer.parseInt(dateFrom.split("-")[0]), Integer.parseInt(dateFrom.split("-")[1]), Integer.parseInt(dateFrom.split("-")[2]));
            LocalDate localDateTo = LocalDate.of(Integer.parseInt(dateTo.split("-")[0]), Integer.parseInt(dateTo.split("-")[1]), Integer.parseInt(dateTo.split("-")[2]));

            String hql = "from FinancialTransaction f where f.transactionDate between :dateFrom and :dateTo and f.transactionUser.id=:userId";
            query = session.createQuery(hql, FinancialTransaction.class);
            query.setParameter("dateFrom", localDateFrom).setParameter("dateTo", localDateTo).setParameter("userId", userId);
        }
        else {
            query = session.createQuery("from FinancialTransaction", FinancialTransaction.class);
        }


        List<FinancialTransaction> financialTransactionList = query.getResultList();

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

}
