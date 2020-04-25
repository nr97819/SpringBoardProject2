package com.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.member.domain.MemberVO;

@Repository
public class  MemberDAOImpl implements MemberDAO {

	// Mapper
	private static String namespace = "com.member.mappers.member";
	
	// MyBatis
	@Inject
	private SqlSession sql;
	
	// 회원가입
	@Override
	public void register(MemberVO vo) throws Exception {

		sql.insert(namespace + ".register", vo);
	}

	// 로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		
		return sql.selectOne(namespace + ".login", vo);
	}

	// 회원탈퇴
	@Override
	public void withdrawl(MemberVO vo) throws Exception {
		
		sql.delete(namespace + ".withdrawl", vo);
	}
	
	// 아이디 중복확인
	@Override
	public MemberVO idCheck(String userId) throws Exception {
		
		return sql.selectOne(namespace + ".idCheck", userId);
	}
	
	// 탈퇴 비밀번호 비교
	@Override
	public MemberVO compPass(String userId) throws Exception{
		
		return sql.selectOne(namespace + ".compPass", userId);
	}
}
