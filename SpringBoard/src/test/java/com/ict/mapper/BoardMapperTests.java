package com.ict.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.persistence.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void getListTest() {
		log.info(mapper.getList());
	}
	
	// insert�� ������ �׽�Ʈ�ڵ带 �ϴܿ� �ۼ��غ��ڽ��ϴ�.
    // @Test
	public void testInsert() {
		// �� �Է��� ���ؼ� BoardVO Ÿ���� �Ű��� �����
		// ���� BoardVO�� ��������
		// setter�� ������, �ۺ���, �۾��̸� �����ص� ä��
		// mapper.insert(vo);�� ȣ���ؼ� ���࿩�θ� Ȯ���ϸ� ��
		// �� ������ ���� �Ʒ� vo�� 6���ۿ� ���� ���� ���� �۾��̸� �ְ�
		// ȣ�����ֽ� ���� ������ DB�� ���� ������ Ȯ�����ּ���.
		BoardVO vo = new BoardVO();
		
		// �Է��� �ۿ� ���� ����, �۾���, ������ vo�� �־��ݴϴ�.
		vo.setTitle("���� �ִ� ��");
		vo.setContent("���� �ִ� ����");
		vo.setWriter("���� �ִ� �۾���");
		
		log.info(vo);
		mapper.insert(vo);
	}
	
	// ���� �׽�Ʈ�ڵ带 �ۼ����ּ���.
	// Long bno �Ķ���Ϳ� ���� �����Ҷ��� ����L�� ����
	// �ڿ� L�� �ٿ����մϴ�.
	// @Test
	public void testDelete() {
		
		mapper.delete(21L);
		
	}
	
    // @Test
	public void testUpdate() {
		
		BoardVO vo = new BoardVO();
		
		// �Է��� �ۿ� ���� ����, �۾���, ������ vo�� �־��ݴϴ�.
		vo.setBno(5L);
		vo.setTitle("�����ؼ� �ִ� ����");
		vo.setContent("�����ؼ� �ִ� ����");
		vo.setWriter("�����ؼ� �ִ� �۾���");
		
		mapper.update(vo);
	}
}
