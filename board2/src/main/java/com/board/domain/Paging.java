package com.board.domain;

public class Paging {
	
	// 현재 페이지 번호
	private int num;
	// 총 게시물 갯수
	private int count;	 
	// 한 페이지에 출력할 게시물 갯수
	private int postNum;
	// 총 페이지 번호 갯수
	private int pageNum; 
	
	
	private int displayPost;	
	// 한번에 표시할 페이지 번호의 갯수
	private int pageNum_cnt;
	
	// 마지막 페이지 번호
	private int endPageNum;
	// 처음 페이지 번호
	private int startPageNum;
	
	// 마지막 페이지 번호 값 저장 (마지막 페이지 여부 확인용 임시 변수)
	private int endPageNum_tmp;
	// 이전 페이지 링크
	private boolean prev;
	// 다음 페이지 링크
	private boolean next;

	
	public Paging() {
		postNum = 10;
		pageNum_cnt = 10;
	}
	
	// setter 메소드 2 개
	
	public void setNum(int num) {
		this.num = num;
	}
	public void setCount(int count) {
		this.count = count;

		// 게시물 총 갯수를 알고난 시점부터 계산을 할 수 있으므로, setCount()에서 메서드를 호출하면 됩니다. 
		dataCalc();
	}
	
	// getter 메소드 7 개
	
	public int getPostNum() {
		return postNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getDisplayPost() {
		return displayPost;
	}
	public int getPageNum_cnt() {
		return pageNum_cnt;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public int getEndPageNum_tmp() {
		return endPageNum_tmp;
	}
	public boolean getPrev() {
		return prev;
	}
	public boolean getNext() {
		return next;
	}
			
	private void dataCalc() {
		
		// 총 페이지 번호 갯수 ( ceil -> [게시물 총 갯수 ÷ 한 페이지에 출력할 갯수] 의 올림 값 사용)
		pageNum = (int)Math.ceil((double)count / postNum); 
		// Math.ceil은 소수점 올림 연산을 해준다. (마지막 페이지에 목록이 가득차지 않아도 페이지가 있어야 하기 때문에)
		 
		/* 출력할 게시물 (ex. 1page인 경우, 1-0=0 이므로, 0번 게시물부터 10개 출력
		 				2page인 경우, 2-1=1이고 1*10=10이므로 10번  게시물부터 10개 출력*/
		displayPost = (num - 1) * postNum;

		// 1차 계산 ---> (표시되는 페이지 번호 중 마지막 번호)
		endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
		/*
		현재 페이지 번호가 8번이라면, 한번에 표시할 페이지 번호의 갯수인 10으로 나눕니다. 
		ex) 8 / 10 = 0.8
		소수점을 올림처리(ceil) 하면 0.8은 1이 됩니다.
		1을 한번에 표시할 페이지 번호의 갯수인 10을 곱하면 10이 됩니다. 
		*/
		
		// 표시되는 페이지 번호 중 첫번째 번호
		startPageNum = (endPageNum - pageNum_cnt) + 1;
		// 마지막 페이지 번호(50) - 표시할 페이지 번호 갯수(10) + 1 = 41 -> (41~50)
		
		if(endPageNum > pageNum) {
			endPageNum = pageNum;
		}
		
		// 2차 계산 ---> (마지막 번호 재계산)
		// 마지막 페이지 번호 값 저장
		endPageNum_tmp = (int)Math.ceil((double)pageNum / (double)pageNum_cnt);
			
		// 이전 페이지가 있는 경우, 항상 10개 페이지번호씩 출력
		prev = startPageNum != 1 ? true : false;
		// 다음 페이지가 마지막 페이지인 경우 의미없는 페이지 미출력 (1차 계산값, 2차 계산값 비교)
		next = pageNum > endPageNum ? true : false;
	}
}
