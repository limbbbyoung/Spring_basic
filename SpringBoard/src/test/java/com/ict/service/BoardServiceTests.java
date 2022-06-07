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
			log.info(service.getList());
		}
		
		// insert�� �׽�Ʈ �� �� ���ּ���.
		// @Test
		public void insertTest() {
			BoardVO vo = new BoardVO();
			vo.setTitle("�ι�°�� ���� �ִ� ��");
			vo.setContent("�ι�°�� ���� �ִ� ����");
			vo.setWriter("�ι�°�� ���� �ִ� �۾���");
			service.insert(vo);
		}
		
		//@Test
		public void deleteTest() {
			long num = 41L;
			service.delete(num);
		}
		
		// ������� �׽�Ʈ
		//@Test
		public void updateTest() {
			BoardVO vo = new BoardVO();
			vo.setBno(42L);
			vo.setTitle("������ ����");
			vo.setContent("������ ����");
			vo.setWriter("������ �۾���");
			service.update(vo);
		}
		
		// detail �׽�Ʈ �ڵ�
		@Test
		public void getDetailTest() {
			long num = 42L;
			service.getDetail(num);
		}
		

}
