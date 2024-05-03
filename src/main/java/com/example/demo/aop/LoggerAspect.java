package com.example.demo.aop;

import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Before("execution(* com.example.demo.services.AccountService.getAccounts(..))")
	public void logMessage(JoinPoint jp) {
		log.info(jp.getSignature().getName()+" is called");
	}
	
	@After("execution(* com.example.demo.services.AccountService.getAccounts(..))")
	public void logMessageAfter(JoinPoint jp) {
		log.info(jp.getSignature().getName()+" is Executed");
	}
	
	@AfterThrowing("execution(* com.example.demo.services.AccountService.getAccounts(..))")
	public void afterThrowError(JoinPoint jp) {
		log.info(jp.getSignature().getName()+" is thrown error");
	}
	
	@AfterReturning("execution(* com.example.demo.services.AccountService.getAccounts(..))")
	public void afterSuccessful(JoinPoint jp) {
		log.info(jp.getSignature().getName()+" is successful");
	}
}
