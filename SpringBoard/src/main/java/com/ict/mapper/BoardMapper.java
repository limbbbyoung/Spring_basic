package com.ict.mapper;

import java.util.List;

import com.ict.persistence.BoardVO;

public interface BoardMapper {

	// board_tbl���� �۹�ȣ 3�� ���ϸ� ��ȸ�ϴ� �������� 
	// ������̼��� �̿��� �ۼ����ּ���.
	// @Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();
	
	// INSERT���� ��������� �޼��带 �����մϴ�.
	// VO���ο� �����ִ� ������ �̿��� insert�� �մϴ�.
	// BoardVO�� �Ű��� insert ������ ���޹���.
	public void insert(BoardVO vo);
	
	// delete�� ������ּ���.
	// �۹�ȣ(LongŸ��)�� �Է� �޾� �ش� �۹�ȣ�� �������ݴϴ�.
	public void delete(long bno);
	
	// update
	public void update(BoardVO vo);
	
}
