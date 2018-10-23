package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.FinancialTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
//        String hql = "select f.transactionId, f.transactionDate, f.transactionAmount, f.transactionDescription, f.category.categoryId " +
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
    public FinancialTransaction getTransactionById(int id) {
        return null;
    }

    @Override
    public void deleteTransactionById(int id) {

    }
}
