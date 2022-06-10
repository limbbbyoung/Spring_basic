package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.persistence.BoardVO;
import com.ict.persistence.PageMaker;
import com.ict.persistence.SearchCriteria;
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
							// @RequestParam의 defaultValue를 통해 값이 안들어올때
							// 자동으로 배정할 값을 정할 수 있음
	public String getList(SearchCriteria cri, Model model) {
		// page 파라미터값이 주어지지 않을때 default 1
		if(cri.getPage() == 0) {
			cri.setPage(1);
		}
		List<BoardVO> boardList = service.getList(cri);
		model.addAttribute("boardList", boardList );
		// PageMaker 생성 
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalBoard(service.getBoardCount());
		log.info(pageMaker);
		model.addAttribute("pageMaker", pageMaker);
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
	
	// 글쓰기는 말 그대로 글을 써주는 로직인데
	// 폼으로 연결되는 페이지가 하나 있어야하고
	// 그 다음 폼에서 날려주는 로직을 처리해주는 페이지가 하나 더 있어야 합니다.
	// /board/insert 를 get방식으로 접속시 
	// boardForm.jsp로 연결되도록 만들어주세요.
	@GetMapping("/insert")
	public String insertBoardForm() {
		return "/board/insertForm";
	}
	
	@PostMapping("/insert")
	public String insertBoard(BoardVO board) {
		log.info(board);
		service.insert(board);
		// redirect를 사용해야 전체 글 목록을 로딩해온 다음 화면을 열어줍니다.
		// 스프링 컨트롤러에서 리다이렉트를 할 때는 
		// 목적주소 앞에 redirect: 을 추가로 붙입니다.
		return "redirect:/board/list";
	}
	
	// 글삭제 post방식으로 처리하도록 합니다.
	@PostMapping("/delete")
	public String deleteBoard(Long bno) {
		// 삭제 후 리스트로 돌아갈 수 있도록 내부 로직을 만들어주시고
		// 디테일 페이지에 삭제 요청을 넣을 수 있는 폼을 만들어주세요.
		service.delete(bno);
		return "redirect:/board/list";
	}
	
	// 글 수정 로직
	@PostMapping("/updateForm")
	public String updateForm(Long bno, Model model) {
		log.info(service.getDetail(bno));
		BoardVO board = service.getDetail(bno);
		model.addAttribute("board", board);
		return "/board/updateForm";
	}
	
	@PostMapping("/update")
	public String udateBoard(BoardVO board) {
		log.info(board);
		service.update(board);
		return "redirect:/board/detail?bno="+board.getBno();
	}
}






