package com.ict.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	// 해당 테스트 코드는 Mapper의 테스트를 담당합니다.
	@Autowired
	private BoardMapper mapper;
	
	// 글목록을 얻어오는 testGetList
	@Test
	public void testGetList() {
		// mapper 내부의 getList 메서드를 호출하려면?
		log.info(mapper.getList());
	}
				
}






