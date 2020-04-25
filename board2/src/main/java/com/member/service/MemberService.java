package com.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.member.domain.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void register(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception;
	
	// 로그아웃
	public void logout(HttpSession session) throws Exception;
	
	// 회원탈퇴
	public void withdrawl (MemberVO vo) throws Exception;
	
	// 아이디 중복확인
	public MemberVO idCheck(String userId) throws Exception;
	
	// 탈퇴 비밀번호 비교
	public MemberVO compPass(String userId) throws Exception;
}
