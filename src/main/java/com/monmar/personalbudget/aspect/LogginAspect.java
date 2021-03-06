package com.monmar.personalbudget.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class LogginAspect {
	
	Logger logger = Logger.getLogger(LogginAspect.class.getName());
	
	@After("execution(* com.monmar.personalbudget.dao.BudgetDao.getBudgetDetailListByBudgetId(..))")
	public void aftereListBudgetDetailFromDao(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toLongString();
		
		logger.info("\n=======>>> Executing @After method: "+method);
		
		
	}
	
	
	@Before("com.monmar.personalbudget.aspect.AopExpressions.forDaoPackage()")
	public void beforeLogginAdvice(JoinPoint joinPoint) {
		
		logger.info("\n=========>>>> Executing @Before advice on method");	
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		
		logger.info("Method: "+methodSignature);
		
		Object[] args = joinPoint.getArgs();
//		arg instanceof User || 	
		
		for(Object arg : args) {
			if(arg instanceof BudgetDetail) {
//				User user = (User) arg;
				BudgetDetail budgetDetail = (BudgetDetail) arg;
//				logger.info("User name: " + user.getUserName());
//				logger.info("Email: "+ user.getEmail());
				logger.info("===========>Budget detail Id: " + budgetDetail.getBudgetDetailId());
				
			}
		}
	}
	@After("com.monmar.personalbudget.aspect.AopExpressions.forDaoPackage()")
	public void afterLogginAdvice(JoinPoint joinPoint) {
		
		logger.info("\n=========>>>> Executing @After advice on method");	
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		
		logger.info("Method: "+methodSignature);
		
		Object[] args = joinPoint.getArgs(); 	
		
		for(Object arg : args) {
			if(arg instanceof User) {
				User user = (User) arg;
				
				logger.info("User name: " + user.getUserName());
				logger.info("Email: "+ user.getEmail());
				
			}
		}
	}
	
	
	@Before("execution(* com.monmar.personalbudget.controller.BudgetController.searchItemByCatName(..))")
	public void afterSerarchBudget(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toLongString();
		
		Object[] args = joinPoint.getArgs();
		
		for(Object arg : args) {
			String name = arg.toString();
			System.out.println(name);
		}
		
		logger.info("\n=======>>> Executing @After method: "+method);
		
		
	}

}
