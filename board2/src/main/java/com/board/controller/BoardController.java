package com.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.Paging;
import com.board.domain.ReplyVO;
import com.board.service.BoardService;
import com.member.controller.MemberController;
import com.member.domain.MemberVO;

@Controller
@RequestMapping("/board/")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	BoardService service;
	
	
	// ----- 게시글 -----
	
	// 게시글 목록
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void getList(Model model) throws Exception{
		
		// Model은 컨트롤러(Controller)와 뷰(View)를 연결해주는 역할을 합니다. 
		List<BoardVO> list = null;
		list = service.list();
		
		model.addAttribute("list", list);
		// 위에서 받아온 list를 list한테 넘겨준다. (이때, 넘겨주는 model의 이름은 list이다.)
	}
	
	// 게시글 작성 (GET)
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void getWrite() throws Exception{
		// 형식상 구현만
	}
	
	// 게시글 작성 (POST)
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception{
		
		service.write(vo);
		
		// 작성을 끝내고 저장버튼을 눌렀다면, 게시글 목록 페이지로 복귀 시킨다.
		return "redirect:/board/listPage?num=1";
	}
	
	// 게시글 조회 
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception{
	/* < @RequestParam("bno") int bno > 이 부분은
		주소 즉, /board/view?bno=[값] 에서 bno의 값을 긁어와서,
		int bno라는 변수에 담아주는 코드이다.
	*/
		BoardVO vo = service.view(bno);
		// service 객체의 view 메소드에 bno 값을 넣고 가져온 객체(model)를 vo에 보관합니다.(밑에서 꺼냄)
		
		model.addAttribute("view", vo);
		// 위에서 받아온 vo 데이터를 view한테 넘겨준다. (이때, 넘겨주는 model의 이름은 view이다.)
		
		
	// 댓글 조회
	// --------------------- 댓글 조회 코드 추가 ------------------------
		List<ReplyVO> reply = service.repList(bno);
		model.addAttribute("reply", reply);
	// --------------------- ------------- ------------------------
		
	// 조회수 count ------------------
		service.updateViewCnt(bno);
	// -----------------------------
		
	}
	
	// 게시글 수정 (GET)
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception{
		
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);
	}
	
	// 게시글 수정(POST)
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception{
		
		service.modify(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	
	// 게시글 삭제
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception{
		
		service.delete(bno);
		// 게시글 목록 첫 페이지로 돌려보낸다.
		return "redirect:/board/listPage?num=1";
	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(@RequestParam("num") int num, Model model) throws Exception {
		
		// (*중요) paging 관련 계산 메소드는 모두 "paging" 파일에서 import 받는다.
		Paging page = new Paging();

		// setter로 num 값 세팅
		page.setNum(num);
		// 총 게시물 갯수 세팅
		page.setCount(service.count());
		 
		
		List<BoardVO> list = service.listPage(page.getDisplayPost(), page.getPostNum());
		
		model.addAttribute("list", list);   
		model.addAttribute("pageNum", page.getPageNum());
		
		/* 시작, 끝 번호 / 이전과 다음 링크 표시를 
			뷰(view)에 출력하기 위해 모델(model)에 넣어둡니다. */
		
		// 시작, 끝 번호
		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
		
		// 이전, 다음 링크
		model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());
		
		// 현재 페이지
		model.addAttribute("now", num);	
	}
	
	
	// ----- 댓글 -----
	
	// 게시글 조회 + 댓글 작성 (댓글 작성 시, 호출됨)
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String regReply(ReplyVO reply, HttpSession session) throws Exception{
		logger.info("regist reply");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		// 접속중인 사용자의 정보 중 id값을 ReplyVO로 가져온다.
		reply.setUserId(member.getUserId());
		
		service.regReply(reply);

		return "redirect:/board/view?bno=" + reply.getBno();
	}
	
	/*
	// 댓글 조회 (Ajax - 비동기식)
	@ResponseBody	// return으로 vo를 통째로 반환해버린다.(Ajax에서 쓰기 위함) 
	// (리턴되는 값은 View 를 통해서 출력되지 않고 HTTP Response Body 에 직접 쓰여지게 됩니다.)
	@RequestMapping(value = "/view/replyList", method=RequestMethod.GET)
	public List<ReplyVO> getReplyList(@RequestParam("bno") int bno) throws Exception {
		logger.info("get reply List");

  		System.out.println("check point controller"); // -------------------------------------
		return service.repList(bno);
	}
	*/
	
	//댓글 수정 (GET)
	@RequestMapping(value="/replyUpdateView", method = RequestMethod.GET)
	public String replyUpdateView(ReplyVO vo, Model model) throws Exception {
		logger.info("reply Write");
		
		model.addAttribute("replyUpdate", service.selectReply(vo.getRno()));
		
		return "board/replyUpdateView";
	}
		
	//댓글 수정 (POST)
	@RequestMapping(value="/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(ReplyVO vo,  RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");
		
		service.updateReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		
		return "redirect:/board/view";
	}
	
	//댓글 삭제 (GET)
	@RequestMapping(value="/replyDeleteView", method = RequestMethod.GET)
	public String replyDeleteView(ReplyVO vo, Model model) throws Exception {
		logger.info("reply Write");
		
		model.addAttribute("replyDelete", service.selectReply(vo.getRno()));
		
		return "board/replyDeleteView";
	}
	
	//댓글 삭제 (POST)
	@RequestMapping(value="/replyDelete", method = RequestMethod.POST)
	public String replyDelete(ReplyVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");
		
		service.deleteReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		
		return "redirect:/board/view";
	}
}


