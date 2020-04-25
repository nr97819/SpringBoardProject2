package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	// ----- 게시글 -----
	
	@Override
	public List<BoardVO> list() throws Exception{	
		return dao.list();
	}

	@Override
	public void write(BoardVO vo) throws Exception {
		dao.write(vo);
	}

	@Override
	public BoardVO view(int bno) throws Exception {
		return dao.view(bno);
		// Inject로 받아오는 dao에 있는 view 메소드에 bno 값을 매개변수로 넘겨주면서 호출한다.
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		dao.modify(vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override // Controller에서 총 페이지 수를 얻기 위함
	public int count() throws Exception {
		return dao.count();
	}

	@Override
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception {
		return dao.listPage(displayPost, postNum);
	}

	
	// ----- 댓글 -----
	
	// 게시글 조회 + 댓글 작성
	@Override
	public void regReply(ReplyVO reply) throws Exception {
		
		dao.regReply(reply);
	}

	// 댓글 조회
	@Override
	public List<ReplyVO> repList(int bno) throws Exception {
		
		return dao.repList(bno);
	}

		
	// 댓글 수정
	@Override
	public void updateReply(ReplyVO reply) throws Exception {
		
		dao.updateReply(reply);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(ReplyVO reply) throws Exception {
		
		dao.deleteReply(reply);
	}

	// 선택된 댓글 조회
	@Override
	public ReplyVO selectReply(int rno) throws Exception {
		
		return dao.selectReply(rno);
	}
		
	
	// ----- etc -----
	
	// 조회수
	@Override
	public void updateViewCnt(int bno) throws Exception {
		
		dao.updateViewCnt(bno);
	}

}
