package com.member.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.member.dao.MemberDAO;
import com.member.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	// 회원가입
	@Override
	public void register(MemberVO vo) throws Exception {

		dao.register(vo);
	}

	// 로그인
	@Override
	public MemberVO login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		
		HttpSession session = req.getSession();
		
		/* view에서 사용자가 입력한 vo 형식의 데이터들을
		     service -> DAO -> DB에 전달 및 비교하고 로그인 가능 여부를 파악해서 가져온다. */
		MemberVO login = dao.login(vo);
		
		/* 로그인이 실패하면 어떠한 값도 넘어오지 않으니 null,
			성공하면 mapper에 있는 query문에 대한 결과가 넘어오게 됩니다. */
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			// 로그인이 실패한 경우, rttr 객체에 ("msg", false) 속성 추가 
			
			/*
			  조건문에 의해 login이 값이 null이라면, 
			msg라는 정보에 false라는 값이 들어가서 전송됩니다. 
			  이 값은 다른 페이지로 이동하거나 새로고침을 하면 없어지는 
			일회용값이다. 
			
			RedirectAttributes가 제공하는 메소드 addFlashAttribute() 는 
			리다이렉트 직전 플래시에 저장하는 메소드라서, 리다이렉트 이후에는 소멸하기 때문이다.
			*/
			
			// header가 아닌 세션을 통해 전달하므로 뒤에 파라미터가 안 보인다. (하지만 GET 방식,,?)
		}

		// 로그인에 성공한 경우 값이 넘어오면, session에 담습니다.
		else
			session.setAttribute("member", login);
		
		return dao.login(vo);
	}

	// 로그아웃
	@Override
	public void logout(HttpSession session) throws Exception {
		
		// controller에서 받아온 session의 정보들을 초기화시킨다.
		session.invalidate();
	}

	// 회원탈퇴
	@Override
	public void withdrawl(MemberVO vo) throws Exception {
		
		dao.withdrawl(vo);
	}

	// 아이디 중복확인
	@Override
	public MemberVO idCheck(String userId) throws Exception {
		
		return dao.idCheck(userId);
	}

	// 탈퇴 비밀번호 비교
	@Override
	public MemberVO compPass(String userId) throws Exception {
		
		return dao.compPass(userId);
	}

}
