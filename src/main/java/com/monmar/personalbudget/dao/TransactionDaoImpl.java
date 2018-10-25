package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.FinancialTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<FinancialTransaction> getTransactionList() {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from FinancialTransaction", FinancialTransaction.class);

        return query.getResultList();
    }

    @Override
    public void saveTransaction(FinancialTransaction financialTransaction) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(financialTransaction);
    }

    @Override
    public List<FinancialTransaction> searchTransactionByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
          String hql = "from FinancialTransaction f where f.category.categoryName like :name";

        if (name != null && name.trim().length() > 0) {
            query = session.createQuery(hql,
                    FinancialTransaction.class);
            query.setParameter("name", "%" + name.toLowerCase() + "%");

        } else {
            query = session.createQuery("from FinancialTransaction", FinancialTransaction.class);
        }

        List<FinancialTransaction> transactionList = query.getResultList();

        return transactionList;
    }

    @Override
    public List<FinancialTransaction> searchTransactionByDate(String dateFrom, String dateTo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;

        if(dateFrom != "" && dateTo != "") {

            LocalDate localDateFrom = LocalDate.of(Integer.parseInt(dateFrom.split("-")[0]), Integer.parseInt(dateFrom.split("-")[1]), Integer.parseInt(dateFrom.split("-")[2]));
            LocalDate localDateTo = LocalDate.of(Integer.parseInt(dateTo.split("-")[0]), Integer.parseInt(dateTo.split("-")[1]), Integer.parseInt(dateTo.split("-")[2]));

            String hql = "from FinancialTransaction f where f.transactionDate between :dateFrom and :dateTo ";
            query = session.createQuery(hql, FinancialTransaction.class);
            query.setParameter("dateFrom", localDateFrom).setParameter("dateTo", localDateTo);
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
