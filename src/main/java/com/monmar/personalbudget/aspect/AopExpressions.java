package com.monmar.personalbudget.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
		
		@Pointcut("execution(* com.monmar.personalbudget.dao.*.*(..))")
		public void forDaoPackage() {}
		
		@Pointcut("execution(* com.monmar.personalbudget.dao.*.get*(..))")
		public void getter() {}

		@Pointcut("execution(* com.monmar.personalbudget.dao.*.set*(..))")
		public void setter() {}
		
		@Pointcut("forDaoPackage() && !(getter() || setter())")
		public void forDaoPackageNoGetterSetter() {}

}
