package com.ict.mapper;

import com.ict.domain.MemberVO;

// Mapper는 하나의 쿼리문을 담당한다.
// 작성해야하는 쿼리문이 있다면 mapper에서의 하나의 메서드를 담당한다고 생각하면 된다.
public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public void insertMemberTbl(MemberVO vo);
	
	public void insertMemberAuth(MemberVO vo);

}
