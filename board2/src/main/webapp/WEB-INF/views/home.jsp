<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<!-- 상단 메뉴를 .jsp를 가져와서 사용 -->
	<jsp:include page="./nav/nav.jsp" />
	
	<head>
		<title>홈페이지</title>
	</head>
	
	<body>
		<h1>HOME</h1>
		<h2>상우's 게시판</h2>
		
		<P>  The time on the server is ${serverTime}. </P>
		
		<!-- 로그인 -->
		
		<!-- session에 사용자의 login 정보가 없는경우 login창 출력 -->
		<c:if test="${member == null}">
			
			<!-- form에서 전송한 데이터를 컨트롤러가 받을 때, 
			     form에서 전송한 데이터 타입과 컨트롤러가 받는 데이터 타입이 일치하는 경우 
			         자동으로 입력이 됩니다. -->
			<!-- form에는 action="/member/login" 을 추가해서 post되는 주소를 정해줍니다. -->
			<form role="form" method="post" autocomplete="off" action="/member/login">
				<p>
					<label for="userId">아이디</label>
					<input type="text" id="userId" name="userId" />
				</p>
				
				<p>
					<label for="userPass">비밀번호</label>
					<input type="password" id="userPass" name="userPass" />
				</p>
				
				<p><button type="submit">로그인</button></p>
				
				<p><a href="/member/register">회원가입</a></p>
			</form>
			
		</c:if>
		
		<c:if test="${register == true}">
			<p style = "color:#0000FF"> <!-- 무시무시하게 정열적인 붉은색 사용 -->
				회원가입되었습니다. <br />
				가입한 아이디로 로그인해주세요.
			</p>
		</c:if>
		
		<!-- MemberController에서 로그인에 실패한경우, 
		msg값이 false가 되도록 설정했던 것을 이용해서 메세지 출력 -->
		<c:if test="${msg == false}">
			<p style = "color:#f00"> <!-- 무시무시하게 정열적인 붉은색 사용 -->
				로그인에 실패하였습니다. <br />
				아이디 또는 패스워드를 다시 확인하고 입력해주십시오.
			</p>
		</c:if>
		
		<!-- session에 사용자의 login 정보가 있는경우 login창 대신 환영 문구 출력 -->
		<c:if test="${member != null}">
		
				<!-- 사용자의 닉네임을 표시 -->
				<p>${member.userName}님 환영합니다.</p>
				
				 <p><a href="/member/logout">로그아웃</a><br/></p>
				 <p><a href="/member/withdrawl">회원탈퇴</a></p>
		</c:if>
	
		<c:if test="${withdrawl == true }">
			 <p style = "color:#0000FF"> <!-- 무시무시하게 정열적인 붉은색 사용 -->
				회원탈퇴되었습니다.<br/>
				이용해주셔서 감사합니다.
			</p>
		</c:if>
		
	</body>
	
</html>
