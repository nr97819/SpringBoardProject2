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
				<h1>댓글 내용 수정</h1>
			</header>
			<hr />
			 
			<div>
				<%@include file="../nav/nav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/board/replyUpdate">
					<input type="hidden" name="bno" value="${replyUpdate.bno}" readonly="readonly"/>
					<input type="hidden" id="rno" name="rno" value="${replyUpdate.rno}" />
					<table>
						<tbody>
							<tr>
								<td>
									<label for="repCon">댓글 내용</label><input type="text" id="repCon" name="repCon" value="${replyUpdate.repCon}"/>
								</td>
							</tr>	
							
						</tbody>			
					</table>
					<div>
						<button type="submit" class="update_btn">저장</button>
						<button type="button" class="cancel_btn">취소</button>
					</div>
				</form>
				
				<script type="text/javascript">
					$(document).ready(function(){
						var formObj = $("form[name='updateForm']");
						
						$(".cancel_btn").on("click", function(){
							location.href = "/board/view?bno=${replyUpdate.bno}";
						})
						
					})
					
				</script>
			</section>
			<hr />
		</div>
	</body>
</html>