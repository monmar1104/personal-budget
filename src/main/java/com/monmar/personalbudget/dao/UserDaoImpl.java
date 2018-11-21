package com.monmar.personalbudget.dao;


import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monmar.personalbudget.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName) {

		Session session = sessionFactory.getCurrentSession();
		logger.info(">>>>>>===============>>>>> User name before Query in findByUserName(): "+userName);
		Query<User> query = session.createQuery("from User where userName=:uName", User.class);
		query.setParameter("uName", userName);
		logger.info(">>>>>>===============>>>>> User name before Query in findByUserName(): "+userName);
		User theUser = null;
		try {
			theUser = query.getSingleResult();
			
		} catch (Exception e) {
			theUser = null;
			logger.info(">>>>>>===============>>>>> User name after findByUserName(): Null ");
		}
		
		logger.info(">>>>>>===============>>>>> User nam after findByUserName(): "+theUser.getUserName());
		return theUser;
	}

	@Override
	public void save(User user) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

}
