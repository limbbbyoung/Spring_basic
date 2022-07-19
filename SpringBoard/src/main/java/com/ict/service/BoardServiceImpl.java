package com.ict.service;

import java.util.List;

import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.mapper.BoardAttachMapper;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;
import com.ict.persistence.BoardAttachVO;
import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;
import com.ict.persistence.SearchCriteria;

import lombok.extern.log4j.Log4j;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<BoardVO> getList(SearchCriteria cri) {
		return mapper.getList(cri);
	}

	@Transactional
	@Override
	public void insert(BoardVO vo) {
		mapper.insert(vo);
		
		if(vo.getAttachList() == null || vo.getAttachList().size()<=0) {
			return;
		}
		
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
	}

	@Transactional
	@Override
	public void delete(long bno) {
		replyMapper.deleteAll(bno);
		
		attachMapper.deleteAll(bno);
		
		mapper.delete(bno);
	}

	@Transactional
	@Override
	public void update(BoardVO vo) {
		
		attachMapper.deleteAll(vo.getBno());
		
		if(vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
		}
		mapper.update(vo);
	} // update end

	@Override
	public BoardVO getDetail(long bno) {
		return mapper.getDetail(bno);
	}

	@Override
	public Long getBoardCount(SearchCriteria cri) {
		Long boardCount = mapper.getBoardCount(cri);
		return boardCount;
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}
	
	
}
