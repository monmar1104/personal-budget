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
}
