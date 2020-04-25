package com.member.dao;

import com.member.domain.MemberVO;

public interface MemberDAO {
	
	// --- 사용자 관리 ---
	
	// 회원가입
	public void register(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;
	
	// 회원탈퇴
	public void withdrawl (MemberVO vo) throws Exception;
	
	// 아이디 중복확인
	public MemberVO idCheck (String userId) throws Exception;
	
	// 탈퇴 비밀번호 비교
	public MemberVO compPass(String userId) throws Exception;
}
