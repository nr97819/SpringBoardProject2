<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- jsp에서 jstl 문법을 사용할 수 있습니다. -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<!-- 상단 메뉴를 .jsp를 가져와서 사용 -->
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
					
					<tr>	<!-- 한 줄을 뜻한다. -->
						<td>${list.bno}</td>
						<td>
							<a href="/board/view?bno=${list.bno}">${list.title}</a>
						</td>
						<td>${list.regDate}</td>
						<td>${list.writer}</td>
						<td>${list.viewCnt}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
		<!-- 테이블(table)이 끝나는 다음줄에 페이징 번호를 출력할 코드를 작성합니다.  --> 
		
		<br />
		
		<div>
		
			<!-- 이전 페이지 번호 출력 링크 -->
			<c:if test="${prev}">
				<span>[ <a href="/board/listPage?num=${startPageNum - 1}">이전</a> ]</span>
			</c:if>
		
			<!-- 1 ~ 마지막 페이지 번호까지 하단에 출력 -->
			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
				<span>
					<c:if test="${now != num}">
						<a href="/board/listPage?num=${num}">${num}</a>
					</c:if>
					
					<c:if test="${now == num}">
						<b>${num}</b>
					</c:if>
				</span>
			</c:forEach>
						
			<!-- 다음 페이지 번호 출력 링크 -->
			<c:if test="${next}">
				<span>[ <a href="/board/listPage?num=${endPageNum + 1}">다음</a> ]</span>
			</c:if>
		</div>
		
	</body>
	
	<jsp:include page="../side/footer.jsp" />
</html>