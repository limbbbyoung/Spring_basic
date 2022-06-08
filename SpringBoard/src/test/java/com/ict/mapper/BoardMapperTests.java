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
	
	//@Test
	public void getListTest() {
		log.info(mapper.getList());
	}
	
	// insert를 실행할 테스트코드를 하단에 작성해보겠습니다.
    // @Test
	public void testInsert() {
		// 글 입력을 위해서 BoardVO 타입을 매개로 사용함
		// 따라서 BoardVO를 만들어놓고
		// setter로 글제목, 글본문, 글쓴이만 저장해둔 채로
		// mapper.insert(vo);를 호출해서 실행여부를 확인하면 됨
		// 위 설명을 토대로 아래 vo에 6번글에 대한 제목 본문 글쓴이를 넣고
		// 호출해주신 다음 실제로 DB에 글이 들어갔는지 확인해주세요.
		BoardVO vo = new BoardVO();
		
		// 입력할 글에 대한 제목, 글쓴이, 본문을 vo에 넣어줍니다.
		vo.setTitle("새로 넣는 글");
		vo.setContent("새로 넣는 본문");
		vo.setWriter("새로 넣는 글쓴이");
		
		log.info(vo);
		mapper.insert(vo);
	}
	
	// 삭제 테스트코드를 작성해주세요.
	// Long bno 파라미터에 값을 전달할때는 정수L과 같이
	// 뒤에 L을 붙여야합니다.
	// @Test
	public void testDelete() {
		
		mapper.delete(21L);
		
	}
	
    // @Test
	public void testUpdate() {
		
		BoardVO vo = new BoardVO();
		
		// 입력할 글에 대한 제목, 글쓴이, 본문을 vo에 넣어줍니다.
		vo.setBno(5L);
		vo.setTitle("수정해서 넣는 제목");
		vo.setContent("수정해서 넣는 본문");
		vo.setWriter("수정해서 넣는 글쓴이");
		
		mapper.update(vo);
	}
	
	@Test
	public void getDetailTest() {
		long num = 42L;
		mapper.getDetail(num);
	}
}