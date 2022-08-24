package com.ict.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.mapper.BoardMapper;
import com.ict.mapper.BoardMapperTests;
import com.ict.persistence.BoardVO;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
		@Autowired
		private BoardService service;
		
		//@Test
		public void getListTest() {
			//log.info(service.getList(6L));
		}
		
		// insert도 테스트 한 번 해주세요.
		// @Testc
		public void insertTest() {
			BoardVO vo = new BoardVO();
			vo.setTitle("두번째로 새로 넣는 글");
			vo.setContent("두번째로 새로 넣는 본문");
			vo.setWriter("두번째로 새로 넣는 글쓴이");
			service.insert(vo);
		}
		
		//@Test
		public void deleteTest() {
			long num = 41L;
			service.delete(num);
		}
		
		// 수정기능 테스트
		//@Test
		public void updateTest() {
			BoardVO vo = new BoardVO();
			vo.setBno(42L);
			vo.setTitle("수정된 제목");
			vo.setContent("수정된 본문");
			vo.setWriter("수정된 글쓴이");
			service.update(vo);
		}
		
		// detail 테스트 코드
		//@Test
		public void getDetailTest() {
			long num = 42L;
			service.getDetail(num);
		}
		

}