package com.monmar.personalbudget.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monmar.personalbudget.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName) {

		Session session = sessionFactory.getCurrentSession();

		Query<User> query = session.createQuery("from User where userName=:uName", User.class);
		query.setParameter("uName", userName);
		User theUser = null;
		try {
			theUser = query.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User user) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

}
