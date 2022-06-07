package com.ict.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.persistence.BoardVO;
import com.ict.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
// 주소 /board
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	// 컨트롤러가 Service를 호출합니다.
	@Autowired
	private BoardService service;
	
	// /board/list 주소로 게시물 전체의 목록을 표현하는 컨트롤러를 만들어주세요.
	@RequestMapping(value="/list",
			method= {RequestMethod.GET, RequestMethod.POST})
	public String getList(Model model) {
		System.out.println("getList 실행");
		List<BoardVO> boardList = service.getList();
		log.info(boardList);
		model.addAttribute("boardList", boardList );
		return "/board/list";
	}
	
	// 글 번호를 입력받아서(주소창에서 bno=? 형식으로) 해당 글의 디테일 페이지를 보여주는
	// 로직을 완성해주세요.
	// board/detail.jsp입니다.
	// getBoardList처럼 포워딩해서 화면에 해당 글 하나에 대한 정보만 보여주면 됩니다.
	@RequestMapping(value="/detail",
			method= {RequestMethod.GET, RequestMethod.POST})
	public String boardDetail(@RequestParam(value="bno")Long bno,Model model) {
		System.out.println("detail 실행");
		BoardVO board = service.getDetail(bno);
		log.info(board);
		model.addAttribute("board", board );
		return "/board/detail";
	}
}






