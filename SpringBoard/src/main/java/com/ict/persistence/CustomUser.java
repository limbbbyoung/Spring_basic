package com.ict.persistence;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

// DB에서 보안적인 검사 없이 바로 데이터를 가져올때는 
// VO를 그대로 활용할 수 있었으나 시큐리티에선 인가된 자료만
// 취급할 수 있으므로 아래와 같이 User를 상속한 클래스를 만들고
// 맴버변수로 VO를 선언해준 다음 생성자에서 
// 아이디(username), 비밀번호(password), 권한(auth), 최소 이 3항목을
// 추후 VO 다음으로 User의 자식클래스를 활용할 수 있습니다.

// private이 걸린 Member를 외부에서 꺼내쓰기위해서 @Getter만 만들어줍니다.
@Getter
public class CustomUser extends User{ // User를 상속하기만 하며 됨. 클래스 이름이 무조건적으로 CustomUser이여야 하는 것은 아님
	
	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	
	public CustomUser(String username, String password,
			Collection<? extends GrantedAuthority> auth) {
		super(username, password, auth);
	}
	
	// CustomUser가 상속한 User클래스 기준으로 생성자를 설정해야 
	// VO와 시큐리티를 연동할 수 있습니다.
	public CustomUser(MemberVO vo) {
		super(
			  vo.getUserid(), // 첫번째 파라미터로 아이디 꺼내주기
			  vo.getUserpw(), // 두번째 파라미터로 비밀번호 꺼내주기
			  
			  // 세번째 파라미터는 List<AuthVO>를
			  // SimpleGrantedAuthority로 변환해서
			  // 입력해야 세팅이 됨
			  vo.getAuthList().stream().map(author -> // .stream().map()이 foreach문 생각하면 됨
			  new SimpleGrantedAuthority(author.getAuth()))
			  .collect(Collectors.toList()));
		      this.member = vo;
	}

}
