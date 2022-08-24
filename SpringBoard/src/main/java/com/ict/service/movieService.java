package com.ict.service;

import java.util.List;
import com.ict.persistence.MovieVO;

public interface movieService {

	// 영화목록
	public List<MovieVO> getList();
}
