package com.board.domain;

import java.util.Date;

/*
	create table tbl_reply (
	    rno     int		primary key 	auto_increment,
	    bno     int 			  not null,
	    userId     varchar(30),
	    repCon     varchar(2000)  not null,
	    repDate    timestamp      default 	now(),
	    foreign key(bno) references tbl_board(bno) on delete cascade,	# 게시글 삭제시 해당 댓글 모두 같이 삭제
	    foreign key(userId) references mymember(userId) on delete set null # 탈퇴한 회원의 댓글은 null 사용자 처리
	);
*/
public class ReplyVO {
	private int rno;
	private int bno;
	private String userId;
	private String repCon;
	private Date repDate;
	
	// 추가 멤버변수(사용자 닉네임)
	private String userName;
	
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRepCon() {
		return repCon;
	}
	public void setRepCon(String repCon) {
		this.repCon = repCon;
	}
	public Date getRepDate() {
		return repDate;
	}
	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}
	
	// 추가 멤버변수(사용자 닉네임)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
