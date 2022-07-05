package com.ict.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	// 테이블 두 개를 조인해서 구성하는 경우
	// 테이블이 조인된 이후를 상정해서 지금과 같이 생성하는 경우가 있고
	// 테이블 두 개를 조인한 VO를 새롭게 하나 생성해주는 방법이 있다.
	// 어떤 것이 더 효율적인지는 스스로 고민해보자.
	private List<AuthVO> authList;
	

}
