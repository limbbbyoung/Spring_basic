package com.ict.mapper;

import java.util.List;

import com.ict.persistence.BoardAttachVO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	// 글마다 첨부된 파일을 찾기 위해 쓰는 메서드
	public List<BoardAttachVO> findByBno(Long bno);
	
	public void deleteAll(Long bno);
	
	public List<BoardAttachVO> getOldFiles();

}
