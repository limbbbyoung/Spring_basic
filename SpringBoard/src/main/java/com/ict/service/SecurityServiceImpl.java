package com.ict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.persistence.MemberVO;
import com.ict.mapper.MemberMapper;

@Service
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private MemberMapper mapper;
	
	// 트렌젝션 걸면 더 좋음
	// @Transactional
	@Override
	public void insertMember(MemberVO vo) {
		mapper.insertMemberTbl(vo);
		
		mapper.insertMemberAuth(vo);
		
	}

}
