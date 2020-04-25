package com.board.domain;

import java.util.Date;

public class BoardVO {
	/*
	이 파일이 적용되는 테이블의 구조는 아래와 같다.
	
	create table tbl_board(
		bno int auto_increment,
		title varchar(50) not null,
		content text not null,
		writer varchar(30) not null,
		regDate timestamp default now(),
		viewCnt int default 0,
		primary key(bno)
	);
 	*/
	
	/* 
	front-end에서 전송하는 데이터 타입을 VO화 시키고, 
	back-end인 컨트롤러에서도 같은 VO타입으로 받도록 해주면 
	알아서 데이터가 들어가기 때문에 편리하기 때문에 VO를 별도로 이용. 
	*/
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
}
