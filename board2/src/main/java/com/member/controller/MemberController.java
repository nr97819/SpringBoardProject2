package com.member.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.member.domain.MemberVO;
import com.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;

	// 회원가입 (GET)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception{
		
		logger.info("get register");
	}
	
	// 회원가입 (POST)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo, RedirectAttributes rttr) throws Exception{
	// register.jsp에서 입력한 vo정보들을 받아온다.
		
		logger.info("post register");
		
		service.register(vo);

		rttr.addFlashAttribute("register", true);
		
		return "redirect:/";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		// 로그인 실패시, 경고메세지를 출력하기 위한 객체로 RedirectAttributes의 인스턴스 rttr을 매개변수에 작성 
		
		logger.info("post login");
		
		HttpSession session = req.getSession();
		
		/* view에서 사용자가 입력한 vo 형식의 데이터들을
		     service -> DAO -> DB에 전달 및 비교하고 로그인 가능 여부를 파악해서 가져온다. */
		MemberVO login = service.login(vo, req, rttr);
		
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
		
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("get logout");
		
		session.invalidate(); // 세션값 초기화 (로그인에 대한 세션 정보를 없애서 로그아웃시킨다.)
		
		return "redirect:/"; // 홈으로 돌려보낸다.
	}
	
	// 회원탈퇴 (GET)
	@RequestMapping(value = "/withdrawl", method = RequestMethod.GET)
	public void getWithdrawl(MemberVO vo) throws Exception{
		logger.info("get withdrawl");
		
	}

	@Inject
	SqlSession sql;
	// 회원탈퇴 (POST)
	@RequestMapping(value = "/withdrawl", method = RequestMethod.POST)
	public String postWithdrawl(@RequestParam("userId") String userId, MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
									// userId 직접 주소에서 뽑아오기
		logger.info("get withdrawl");

			// session 방식
		// HttpSession session = req.getSession();
		// MemberVO member = (MemberVO)session.getAttribute("member");
		
		// new code !!
		MemberVO member2 = service.compPass(userId);
		
			// session을 사용하는 방식
		//String realPass = member.getUserPass();
			// DB에서 직접 찾아오는 방식
		String realPass2 = member2.getUserPass();
		String nowPass = vo.getUserPass();

			// 값 확인용 코드
		// System.out.println(session.getAttribute("member"));
		//System.out.println(realPass);
		System.out.println(realPass2);
		System.out.println(nowPass);
		
		/* 비밀번호 비교 코드 */
		if(!(realPass2.equals(nowPass))) {
			rttr.addFlashAttribute("msg", false);
			
			return "redirect:/member/withdrawl";
		}
		
		
		// 처리
		service.withdrawl(vo);
		
		// 회원탈퇴 - 세션값 초기화 (로그인에 대한 세션 정보를 없애서 로그아웃시킨다.)
		HttpSession session = req.getSession();
		session.invalidate(); 
		
		rttr.addFlashAttribute("withdrawl", true);
		
		return "redirect:/";
	}
	
	// 아이디 중복확인
	@ResponseBody // Ajax사용하기에 별도 jsp파일이 필요 없다.
	@RequestMapping(value ="/idCheck", method=RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception{
		logger.info("post idCheck");
		
		String userId = req.getParameter("userId");
		MemberVO idCheck = service.idCheck(userId);
		
		int result = 0;
		
		// 결과가 null이 아니라면, select문에서 중복되는 id를 받아왔다는 것
		if(idCheck != null) {
			// 중복처리
			result = 1; // 중복인 숫자를 반환 (1)
		}
		// 결과가 null이면 중복 X
		return result; // 중복이 아닌 숫자를 반환 (0)
	}
}
