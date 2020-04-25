package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

public interface BoardService {
	
	// ----- 게시판 -----
	
	// 게시글 목록
	public List<BoardVO> list() throws Exception;
	
	// 게시글 작성
	public void write(BoardVO vo) throws Exception;
	
	// 게시물 조회
	public BoardVO view(int bno) throws Exception;
	
	// 게시글 수정
	public void modify(BoardVO vo) throws Exception;
	
	// 게시글 삭제
	public void delete(int bno) throws Exception;
	
	// 게시글 총 갯수
	public int count() throws Exception;
	
	// 게시글 목록 + 페이징
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception;
	
	
	// ----- 댓글 -----
	
	// 게시글 조회 + 댓글 작성
	public void regReply(ReplyVO reply) throws Exception;
	
	// 댓글 조회
	public List<ReplyVO> repList(int bno) throws Exception;

	
	// 댓글 수정
	public void updateReply(ReplyVO reply) throws Exception;
	
	// 댓글 삭제
	public void deleteReply(ReplyVO reply) throws Exception;
	
	// 선택된 댓글 조회
	public ReplyVO selectReply(int rno) throws Exception;
	
	
	// ----- etc -----
	
	// 조회수
	public void updateViewCnt(int bno) throws Exception;
	
}
