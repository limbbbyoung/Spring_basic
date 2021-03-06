package com.ict.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.domain.AuthVO;
import com.ict.domain.MemberVO;
import com.ict.service.SecurityService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {
	
	@Autowired
	private SecurityService service;
	
	@Autowired
	private PasswordEncoder pwen; // PasswordEncoder Interface
								  // 내부에 encode 메서드를 통해 암호화를 진행할 수 있음
	
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
	public void join(MemberVO vo, String[] role) {
		// 실제로 코드를 짤 때 아래와 같이 디버깅을 세부적으로 해줄 필요는 없는데 
		// 코드가 익숙해지고 이해될때까지는 디버깅을 섬세하게 해주는 것이 좋다.
		// 코드의 사용이 익숙해지면 디버깅은 따로 해주지 않아도 됨
		String beforeCrPw = vo.getUserpw();
		log.info("암호화 전 : " + vo.getUserpw());
		vo.setUserpw(pwen.encode(beforeCrPw));
		log.info("암호화 후 : " + vo.getUserpw());
		
		// null 상태인 authList에 빈 ArrayList를 먼저 배정
		vo.setAuthList(new ArrayList<AuthVO>());
		
		// authList는 List<authList>이므로 권한 개수에 맞게 넣어줘야함
		/* for(int i = 0; i < role.length; i++) {
			vo.getAuthList().add(new AuthVO());
			vo.getAuthList().get(i).setAuth(role[i]);
			vo.getAuthList().get(i).setUserid(vo.getUserid());
		} */
		
		// 향상된 for문 활용
		for(String roleItem : role) {
			AuthVO authVO = new AuthVO();
			authVO.setAuth(roleItem);
			authVO.setUserid(vo.getUserid());
			
			log.info("권한 갯수만큼 AuthVO 생성하는지 체크 : " + authVO);
			// 체크가 되었다면 vo내부의 권한목록에 집어넣기
			vo.getAuthList().add(authVO);
		}
		log.info("가입시 받는 데이터들 : " + vo);
		log.info(vo.getAuthList());
		log.info("사용자가 선택한 권한목록 : " + Arrays.toString(role));
		// vo에 데이터가 전부 들어온 것을 확인했으므로 회원가입 로직 실행되도록 처리
		service.insertMember(vo); // 해당 서비스의 insertMember를 실행함으로써 DB에 tbl, auth 테이블 둘 다 적용됨
	}

}
