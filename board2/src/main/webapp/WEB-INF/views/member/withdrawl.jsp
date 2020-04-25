<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 탈퇴</title>
	</head>
	<body>
		<h2>회원 탈퇴</h2>
		
		<form role="form" method="post" autocomplete="off">
			<p>
				<label for="userId">탈퇴할 아이디</label>
				<input type="text" id="userId" name="userId" value="${member.userId}" />
			</p>
			<p>
				<label for="userPass">비밀번호</label>
				<input type="password" id="userPass" name="userPass" />
			</p>
			<p>
				<button type="submit">탈퇴</button>
			</p>
			<p>
				<a href="/">되돌아가기</a>
			</p>
		</form>
		
		<c:if test="${msg == false }">
			 <p style = "color:#f00"> <!-- 무시무시하게 정열적인 붉은색 사용 -->
				비밀번호가 틀립니다!
			</p>
		</c:if>
		
	</body>
</html>