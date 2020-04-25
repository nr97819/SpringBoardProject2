package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;

@Repository
public class BoardDaoImpl implements BoardDAO {

	@Inject
	private SqlSession sql;
	
	
	// BoardDAOImpl에 있는 namespace는 매퍼의 namespace와 일치해야합니다. 
	private static String namespace = "com.board.mappers.board";
	// ReplyDAOImpl ,,,
	private static String namespaceReply = "com.board.mappers.reply";
	
	
	@Override
	public List<BoardVO> list() throws Exception {
	// 여러 줄의 BoardVO를 사용하기에 List를 사용
		
		return sql.selectList(namespace + ".list");
		// boardMapper에서 id=list인 쿼리문을 list<E> 형태(여러줄)로 가져온다.
	}

	@Override
	public void write(BoardVO vo) throws Exception {

		// boardMapper에 있는 쿼리를 이용하여 데이터 베이스에 데이터를 넣을 수 있도록 코드를 추가합니다. 
		sql.insert(namespace + ".write", vo);
	}

	@Override
	public BoardVO view(int bno) throws Exception {
	// list와는 달리, 찾고자 하는 bno를 가진 한 줄의 BoardVO를 가져오기 때문에 반환형은 BoardVO
		
		return sql.selectOne(namespace + ".view", bno);
		// boardMapper에서 id=view인 쿼리문의 결과인 레코드를 1개(1줄)만 가져온다.
	}

	@Override
	public void modify(BoardVO vo) throws Exception {

		sql.update(namespace + ".modify", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		
		sql.delete(namespace + ".delete", bno);
	}

	@Override
	public int count() throws Exception {
		
		return sql.selectOne(namespace + ".count");
	}

	@Override
	public List listPage(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
		
		/* DAO와 매퍼에서는 데이터를 하나만 전송할 수 있기 때문에, 
		2개 이상의 데이터를 다룰 때는 VO(Value Object)를 사용하거나 해시맵을 이용합니다.*/ 	
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		// 매개변수인 displayPost,postNum를 해시맵을 이용하여 하나로 그룹지어준 뒤 매퍼에 전송합니다. 
		
		return sql.selectList(namespace + ".listPage", data);
	}

	
	// ----- 댓글 -----
	
	// 게시글 조회 + 댓글 작성
	@Override
	public void regReply(ReplyVO reply) throws Exception {
		
		sql.insert(namespaceReply + ".regReply", reply);
	}

	// 댓글 조회
	@Override
	public List<ReplyVO> repList(int bno) throws Exception {
		
		return sql.selectList(namespaceReply + ".repList", bno);
	}
	
	// 댓글 수정
	@Override
	public void updateReply(ReplyVO reply) throws Exception {
		
		sql.update(namespaceReply + ".updateReply", reply);
	}
	
	// 댓글 삭제
	@Override
	public void deleteReply(ReplyVO reply) throws Exception {
		
		sql.delete(namespaceReply + ".deleteReply", reply);
	}
	
	// 선택된 댓글 조회
	@Override
	public ReplyVO selectReply(int rno) throws Exception {
		
		return sql.selectOne(namespaceReply + ".selectReply", rno);
	}
	
	
	// ----- etc -----
	
	// 조회수
	@Override
	public void updateViewCnt(int bno) throws Exception {
		
		sql.update(namespace + ".updateViewCnt", bno);
	}

}
