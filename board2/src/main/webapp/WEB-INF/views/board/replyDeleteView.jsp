<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		 <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	 	<title>게시판</title>
	</head>
	<body>
	
		<div id="root">
			<header>
				<h1>삭제 확인</h1>
			</header>
			<hr />
			 
			<div>
				<%@include file="../nav/nav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/board/replyDelete">
					<input type="hidden" name="bno" value="${replyDelete.bno}" readonly="readonly"/>
					<input type="hidden" id="rno" name="rno" value="${replyDelete.rno}" />
						
					<div>
						<p>정말 삭제 하시겠습니까?</p>
						<button type="submit" class="delete_btn">네, 삭제합니다.</button>
						<button type="button" class="cancel_btn">아니오, 삭제하지 않습니다.</button>
					</div>
				</form>
				
				<script type="text/javascript">
					$(document).ready(function(){
						var formObj = $("form[name='updateForm']");
						
						$(".cancel_btn").on("click", function(){
							location.href = "/board/view?bno=${replyDelete.bno}";
						})
						
					})
				</script>
			</section>
			<hr />
		</div>
	</body>
</html>