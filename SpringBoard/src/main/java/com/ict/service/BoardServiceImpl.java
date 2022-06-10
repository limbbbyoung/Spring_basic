package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.mapper.BoardMapper;
import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;

import lombok.extern.log4j.Log4j;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.getList(cri);
	}

	@Override
	public void insert(BoardVO vo) {
		mapper.insert(vo);
	}

	@Override
	public void delete(long bno) {
		mapper.delete(bno);
	}

	@Override
	public void update(BoardVO vo) {
		mapper.update(vo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		return mapper.getDetail(bno);
	}

	@Override
	public Long getBoardCount() {
		Long boardCount = mapper.getBoardCount();
		System.out.println(boardCount);
		return boardCount;
	}
	
	
}
