package com.ict.service;

import java.util.List;

import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;
import com.ict.persistence.SearchCriteria;

public interface BoardService {

	// Service는 원래 하나의 동작(사용자 기준)을 선언하고
	// Mapper는 하나의 호출(쿼리문)을 선언하는 용도입니다.
	// 그런데 기본적인 로직은 하나의 동작이 하나의 쿼리문으로 
	// 현재는 그냥 로직별로 하나씩 메서드를 만들어주시면 됩니다.
	// 단, 나중에 사용자에게는 글삭제이지만, 백로직에서는 글과 댓글이 모두 삭제된다던지...하는 식으로
	// 사용자 기준의 하나의 동작과 로직개념적 하나의 동작이 일치하지 않을수도 있으니 주의해야합니다.
	
	// getList
	public List<BoardVO> getList(SearchCriteria cri);
	
	// insert
	public void insert(BoardVO vo);
	
	// delete
	public void delete(long bno);
	
	// update
	public void update(BoardVO vo);
	
	// detail
	public BoardVO getDetail(long bno);
	
	// 전체 글 갯수 가져오기
	public Long getBoardCount(SearchCriteria cri);

}