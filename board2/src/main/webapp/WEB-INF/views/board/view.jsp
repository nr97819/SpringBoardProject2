<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<jsp:include page="../nav/nav.jsp" />

	<head>
		<meta charset="UTF-8">
		<title>게시글 조회</title>
	</head>
	
	<body>
		<h2>게시글 조회</h2>
	
		<label>제목</label> <br />
		${view.title} <br /> <hr />
		
		<label>작성자</label> <br />
		${view.writer} <br /> <hr />
		
		<label>내용</label> <br />
		${view.content} <br /> <hr />
		<!-- view.xxx 을 통해, controller에서 model.addAttribute()한 값들을 가져온다. -->
	
		<div class="container">
			<a href="/board/modify?bno=${view.bno}">게시물 수정</a>
			
			<a href="/board/delete?bno=${view.bno}">게시물 삭제</a> <br /><br />
			
			<a href="/board/listPage">리스트로</a> <br /><br />
		</div>
		
		<!-- 댓글 리스트 포함 -->
		<jsp:include page="./reply.jsp" />
		
	</body>
	
	<jsp:include page="../side/footer.jsp" />
	
</html>