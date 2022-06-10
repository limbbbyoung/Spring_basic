package com.ict.persistence;

import lombok.Data;

@Data
public class PageMaker {
	
	private Long totalBoard;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum;
	// 현재 조회중인 페이지 정보를 획득하기 위해 선언
	private Criteria cri;
	
	public void calcDate() {
		// 출력페이지 개수
		this.displayPageNum = 10;
		
		// 끝페이지 = 올림((현재페이지/출력페이지, 실수)) * 출력페이지(현재10개씩)
		this.endPage = (int)(Math.ceil(cri.getPage() / 
				(double) displayPageNum) * displayPageNum);
		
		// 시작페이지 = (끝페이지-출력페이지개수)+1
		this.startPage = (this.endPage - this.displayPageNum) + 1;
		
		// 마지막 페이지의 페이지버튼 수 정하기
		int tempEndPage = (int)(Math.ceil(totalBoard / (double)cri.getNumber()));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// 이전 페이지는 startPage 변수가 1이면 뜨면 안됨
		this.prev = this.startPage == 1 ? false : true;
		
		// 다음 페이지
		this.next = this.endPage * cri.getNumber() >= totalBoard ? false : true;
	}
	
	public void setTotalBoard(Long totalBoard) {
		this.totalBoard = totalBoard;
		
		calcDate();
	}
}
