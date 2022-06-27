package com.ict.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect
public class LogAdvice {
		
		@Before("execution(* com.ict.service.SampleService*.*(..))") // 핵심로직 실행전 보조로직을 실행하겠음(Before), excution은 표현식
		public void logBefore() {
			log.info("===============");
		}
		
		@Before("execution(* com.ict.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
		public void logBeforeWithParam(String str1, String str2) {
			log.info("str1 : " + str1);
			log.info("str2 : " + str2);
		}
		
		@AfterThrowing(pointcut = "execution(* com.ict.service.SampleService*.*(..))", throwing="exception")
		public void logException(Exception exception) {
			log.info("Exception...!!!!");
			log.info("exception : " + exception);
		}
		
		@Around("execution(* com.ict.service.SampleService*.*(..))")
		public Object logTime(ProceedingJoinPoint pjp) { // 어떤 메서드를 실행할지는 정해졌지만 바로 실행하지 않고
														// 일종의 사전 실행 준비 작업을 하고 proceed를 통해 실행 후
														// 뒤에 따라오는 로직들을 나머지 실행해줌
			
			// AOP를 실질적으로 우리가 진행하는 프로젝트 레벨에서는 많이 활용하지 않기 때문에
			// 어떤식으로 돌아가고 이렇게 활용하는 것이구나라는 이해도를 가지면 됨
			// 직접 AOP를 작성해보면서 그 구성이 어떻게 돌아가는지 이해하면 된다.
			long start = System.currentTimeMillis();
			
			log.info("Target : " + pjp.getTarget());
			log.info("Param : " + Arrays.toString(pjp.getArgs()));
			
			Object result = null;
			
			try {
				// 이 시점에서야 타겟 메서드가 실행됨
				result = pjp.proceed();
			} catch(Throwable e) {
				e.printStackTrace();
			}
			
			long end = System.currentTimeMillis();
			log.info("Time : " + (end - start));
			return result;
		}
}
