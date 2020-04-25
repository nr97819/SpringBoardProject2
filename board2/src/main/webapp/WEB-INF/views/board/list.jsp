<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- jsp에서 jstl 문법을 사용할 수 있습니다. -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<!-- 상단 메뉴를 nav.jsp를 가져와서 사용 -->
	<jsp:include page="../side/header.jsp" />
	<jsp:include page="../nav/nav.jsp" />
	
	<head>
		<meta charset="UTF-8">
		<title>게시글 목록</title>
	</head>
	
	<body>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>
			</thead>
		 
			<tbody> 
			<!-- 컨트롤러(Controller)에서 받아온 데이터를 출력하기 위해 
					jstl의 반복문(c:forEach)을 이용하여 출력합니다. -->
							
				<c:forEach var="list" items="${list}"> 
					<!-- forEach 를 사용하여 List의 갯수만큼 반복합니다. -->
					<!-- items의 값을 var로 받아 그 내에서는 var의 값으로 사용합니다. -->
					
					<tr>
						<td>${list.bno}</td>
						<td>
							<a href="/board/view?bno=${list.bno}">${list.title}</a>
						</td>
						<td>${list.title}</td>
						<td>${list.regDate}</td>
						<td>${list.writer}</td>
						<td>${list.viewCnt}</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</body>
	
	<jsp:include page="../side/footer.jsp" />
	
</html>