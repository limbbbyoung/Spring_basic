package com.ict.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.persistence.BoardAttachVO;
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
	
	@PreAuthorize("permitAll")
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
		pageMaker.setTotalBoard(service.getBoardCount(cri));
		log.info(service.getBoardCount(cri));
		log.info(pageMaker);
		model.addAttribute("pageMaker", pageMaker);
		return "/board/list";
	}
	
	// 글 번호를 입력받아서(주소창에서 bno=? 형식으로) 해당 글의 디테일 페이지를 보여주는
	// 로직을 완성해주세요.
	// board/detail.jsp입니다.
	// getBoardList처럼 포워딩해서 화면에 해당 글 하나에 대한 정보만 보여주면 됩니다.
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/insert")
	public String insertBoardForm() {
		return "/board/insertForm";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@PostMapping("/insert")
	public String insertBoard(BoardVO board) {

		service.insert(board);
		
		log.info("=====================");
		log.info("register : " + board);
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach)); // AttachList 안에 있는 데이터를 
		}															// attach라는 변수에 하나하나에 담아서 
																	// log.info(attach)를 실행
		// redirect를 사용해야 전체 글 목록을 로딩해온 다음 화면을 열어줍니다.
		// 스프링 컨트롤러에서 리다이렉트를 할 때는 
		// 목적주소 앞에 redirect: 을 추가로 붙입니다.
		return "redirect:/board/list";
	}
	
	// 글삭제 post방식으로 처리하도록 합니다.
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/delete")
	public String deleteBoard(Long bno, SearchCriteria cri, RedirectAttributes rttr, Model model) {
		// 삭제할 로직의 첨부파일 목록을 먼저 다 가지고 옵니다.
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		// 삭제 후 리스트로 돌아갈 수 있도록 내부 로직을 만들어주시고
		// 디테일 페이지에 삭제 요청을 넣을 수 있는 폼을 만들어주세요.
		// 아래 로직은 DB에 있던 정보만 삭제함
		service.delete(bno);
		
		// attachList에 들어있는 정보를 토대로 C: 의 파일까지 삭제
		// 단 첨부파일이 없는데도 삭제로직을 돌릴 필요는 없으므로
		// 미리 첨부파일이 있는지 여부를 확인해서 돌립니다
		if(attachList != null || attachList.size() > 0) {
			deleteFiles(attachList);
		}
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
	
	// 글 수정 로직
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/updateForm")
	public String updateForm(Long bno, Model model) {
		BoardVO board = service.getDetail(bno);
		model.addAttribute("board", board);
		return "/board/updateForm";
	}
	
	@PostMapping("/update")
	public String udateBoard(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) {
		log.info("수정로직입니다." + board);
		log.info("검색어 : " + cri.getKeyword());
		log.info("검색조건 : " + cri.getSearchType());
		log.info("페이지번호 : " + cri.getPage());
		service.update(board);
		
		// rttr.addAttribute("파라미터명", "전달자료")
		// 는 호출되면 redirect 주소 뒤에 파라미터를 붙여줍니다.
		// rttr.addFlashAttribute()는 넘어간 페이지에서 파라미터를
		// 쓸 수 있도록 전달하는 것으로 둘의 역할이 다르니 주의해주세요.
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/detail";
	} // updateBoard end
	
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	} // getAttachList end
	
	
	// 파일 폴더에서 첨부 파일에 대한 데이터 삭제를 위한 메서드, 삭제 보조 메서드
	private void deleteFiles(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\upload_data\\temp\\" + attach.getUploadPath() + 
									"\\" + attach.getUuid() + "_" + attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					
					Path thumbNail = Paths.get("C:\\upload_data\\temp\\" + attach.getUploadPath()
												+ "\\s_" + attach.getUuid() + "_" + attach.getFileName());
					
					Files.delete(thumbNail);
				}
			} catch(Exception e) {
				log.error(e.getMessage());
			}// end catch
		}); // end foreach
	} // deleteFiles END
	
}





