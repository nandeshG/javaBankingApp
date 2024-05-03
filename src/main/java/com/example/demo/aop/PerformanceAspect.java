package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
	private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);
	
	@Around("execution(* com.example.demo.services.AccountService.getAccounts(..))")
	public Object performance(ProceedingJoinPoint jp) throws Throwable {
		
		long start = System.currentTimeMillis();
		Object obj = jp.proceed();
		long end = System.currentTimeMillis();
		log.info("time taken to execute "+jp.getSignature().getName()+" : "+(end-start));
		return obj;
	}
}
