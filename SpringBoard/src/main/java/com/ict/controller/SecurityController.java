package com.ict.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.persistence.AuthVO;
import com.ict.persistence.MemberVO;
import com.ict.service.SecurityService;

import lombok.extern.log4j.Log4j;

// 스프링 시큐리티의 핵심은 간단하게 어노테이션을 걸어주는것만으로도 
// 권한에 따른 페이지 접속을 관리할 수 있다는 점이다.
// 직접 구성하면 까다로운 로직들을 간단하게 관리, 사용이 가능하다는 점이 큰 장점이다
@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {
	
	@Autowired
	private SecurityService service;
	
	@Autowired
	private PasswordEncoder pwen;
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all 로직");
	}
	
	// 컨트롤러 위에 PreAuthorize 라는 어노테이션을 걸면 권한에 따른 접근을 설정할 수 있음
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들이 접속 가능한 member 로직");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin(Principal principal) { // principal 타입을 파라미터에 선언하면 컨트롤러에서 제어가능
		log.info("운영자 접속 : " + principal); // 로그인한 사용자 아이디를 getName()으로 가져올 수 있음
		log.info("운영자만 접속 가능한 admin 로직");
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/join")
	public void joinForm() {
		log.info("회원가입창 접속");
	}
	
	@PreAuthorize("permitAll")
	@PostMapping("/join")
	public String join(MemberVO vo, String[] role) {
		
		log.info("입력 받은 회원의 이름 : " + vo.getUserName());
		
		String beforeCrPw = vo.getUserpw();
		vo.setUserpw(pwen.encode(beforeCrPw));
		log.info("암호화 후 : " + vo.getUserpw());
		
		// null 상태인 authList에 빈 ArrayList를 먼저 배정
		vo.setAuthList(new ArrayList<AuthVO>());

		// 향상된 for문 활용
		for(String roleItem : role) {
			AuthVO authVO = new AuthVO();
			authVO.setAuth(roleItem);
			authVO.setUserid(vo.getUserid());
			
			log.info("권한 갯수만큼 AuthVO 생성하는지 체크 : " + authVO);
			
			// 체크가 되었다면 vo내부의 권한목록에 집어넣기
			vo.getAuthList().add(authVO);
		}
		log.info(vo.getAuthList());
		// vo에 데이터가 전부 들어온 것을 확인했으므로 회원가입 로직 실행되도록 처리
		service.insertMember(vo); 
		
		return "redirect:/board/list";
	}
	
	

}
