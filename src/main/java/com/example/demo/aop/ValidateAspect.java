package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateAspect {

	private static final Logger log = LoggerFactory.getLogger(ValidateAspect.class);
	
	@Around("execution(* com.example.demo.services.AccountService.getAccount(..)) && args(id)")
	public Object validateInput(ProceedingJoinPoint jp,Long id) throws Throwable {
		if(id<0) {
			log.info("changing id");
			id = -id;
		}
		Object obj = jp.proceed(new Object[] {id});
		
		return obj;
	}
}
