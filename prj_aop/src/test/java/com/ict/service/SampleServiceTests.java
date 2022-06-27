package com.ict.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {
	
	@Autowired
	private SampleService service;
	
	//@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	
	// AOP의 이해를 위해선 좀 더 깊은 학습이 필요하지만
	// 간단하게 보조로직을 추가해서 핵심로직에 더해주는 것이라고 생각하면 된다.
	@Test
	public void testAdd() throws Exception { 
		log.info(service.doAdd("123", "456"));
	}

}
