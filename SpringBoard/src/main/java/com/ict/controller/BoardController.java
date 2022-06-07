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
// �ּ� /board
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	// ��Ʈ�ѷ��� Service�� ȣ���մϴ�.
	@Autowired
	private BoardService service;
	
	// /board/list �ּҷ� �Խù� ��ü�� ����� ǥ���ϴ� ��Ʈ�ѷ��� ������ּ���.
	@RequestMapping(value="/list",
			method= {RequestMethod.GET, RequestMethod.POST})
	public String getList(Model model) {
		System.out.println("getList ����");
		List<BoardVO> boardList = service.getList();
		log.info(boardList);
		model.addAttribute("boardList", boardList );
		return "/board/list";
	}
	
	// �� ��ȣ�� �Է¹޾Ƽ�(�ּ�â���� bno=? ��������) �ش� ���� ������ �������� �����ִ�
	// ������ �ϼ����ּ���.
	// board/detail.jsp�Դϴ�.
	// getBoardListó�� �������ؼ� ȭ�鿡 �ش� �� �ϳ��� ���� ������ �����ָ� �˴ϴ�.
	@RequestMapping(value="/detail",
			method= {RequestMethod.GET, RequestMethod.POST})
	public String boardDetail(@RequestParam(value="bno")Long bno,Model model) {
		System.out.println("detail ����");
		BoardVO board = service.getDetail(bno);
		log.info(board);
		model.addAttribute("board", board );
		return "/board/detail";
	}
}






