package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.mapper.movieMapper;
import com.ict.persistence.MovieVO;

@Service
public class movieServiceImpl implements movieService{

	@Autowired
	private movieMapper mapper;
	
	@Override
	public List<MovieVO> getList() {
		return mapper.getList();
	}
	
}
