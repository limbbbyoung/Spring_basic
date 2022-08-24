package com.ict.mapper;

import java.util.List;
import com.ict.persistence.MovieVO;

public interface movieMapper {

	// getList(데이터 불러오기)
	public List<MovieVO> getList();
	
	// insert
	public void insert(MovieVO vo);
	
	// delete
	public void delete(long rnum);
	
	// update
	public void update(MovieVO vo);
	
	
}
