package com.ict.mapper;

import java.util.List;

import com.ict.persistence.BoardVO;

public interface BoardMapper {

	// board_tbl���� �۹�ȣ 3�� ���ϸ� ��ȸ�ϴ� �������� 
	// ������̼��� �̿��� �ۼ����ּ���.
	// @Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();
}
