package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.ict.persistence.MovieVO;
import com.ict.service.movieService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/movie")
@Log4j
public class movieController {
	
	// 컨트롤러가 Service를 호출합니다.
	@Autowired
	private movieService service;
	
	@RequestMapping(value="/list",
			method= {RequestMethod.GET, RequestMethod.POST})
							// @RequestParam의 defaultValue를 통해 값이 안들어올때
							// 자동으로 배정할 값을 정할 수 있음
	public String getList(Model model) {
		List<MovieVO> movieList = service.getList();
		model.addAttribute("movieList", movieList );
		return "/movie/list";
	}

}
