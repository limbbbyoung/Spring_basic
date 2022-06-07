package com.ict.service;

import java.util.List;

import com.ict.persistence.BoardVO;

public interface BoardService {

	// Service�� ���� �ϳ��� ����(����� ����)�� �����ϰ�
	// Mapper�� �ϳ��� ȣ��(������)�� �����ϴ� �뵵�Դϴ�.
	// �׷��� �⺻���� ������ �ϳ��� ������ �ϳ��� ���������� 
	// ����� �׳� �������� �ϳ��� �޼��带 ������ֽø� �˴ϴ�.
	// ��, ���߿� ����ڿ��Դ� �ۻ���������, ����������� �۰� ����� ��� �����ȴٴ���...�ϴ� ������
	// ����� ������ �ϳ��� ���۰� ���������� �ϳ��� ������ ��ġ���� �������� ������ �����ؾ��մϴ�.
	
	// getList
	public List<BoardVO> getList();
	
	// insert
	public void insert(BoardVO vo);
	
	// delete
	public void delete(long bno);
	
	// update
	public void update(BoardVO vo);
	
	// detail
	public BoardVO getDetail(long bno);

}
