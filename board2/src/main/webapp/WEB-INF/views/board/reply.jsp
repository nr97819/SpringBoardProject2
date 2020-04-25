<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- tag;lib -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!-- 부트스트랩 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div id="reply">
		
			 <!-- 로그오프 되어있다면  (session에 등록된 로그인 정보 활용)-->
			 <c:if test="${member == null}">
			  	<p>댓글을 남기시려면 <a href="/">로그인을 </a>해주세요</p>
			 </c:if>
			 
			 <!-- 로그인 되어있다면 (,,,)-->
			 <c:if test="${member != null}">
				 <section class="replyForm">
					  <form role="form" method="post" autocomplete="off">
					  <!-- 댓글 등록 버튼 클릭 시, post 방식으로 데이터 반환 ** -->
					  
					  	<!-- 게시글 번호(bno)를 "댓글과 함께"
					  	controller에 전달하기 위헤 form 안에 숨겨진 input box 추가 -->
					 	   <input type="hidden" name="bno" value="${view.bno}">
					  
						   <div class="input_area">
						   		 <textarea name="repCon" id="repCon"></textarea>
						   </div>
						   
						   <div class="input_area">
						   	 	<button type="submit" id="reply_btn">댓글 등록</button>
						   </div>
						   
					  </form>
				 </section>
			 </c:if>
			 
			 
			 <p><h3>댓글</h3></p>
			 
			 <section class="replyList">
			 
				 <ul>
				 
				 	 <!-- 댓글 갯수만큼 반복 (모두 출력/조회) -->
					 <c:forEach items="${reply}" var="reply">
					
					 	 <li>
						      <div class="userInfo">
						   			  작성자 :
							       <span class="userName">${reply.userName}(${reply.userId})</span>
							     	  작성일 :
							       <span class="date"><fmt:formatDate value="${reply.repDate}" pattern="yyyy-MM-dd" /></span>
						      </div>
						      <div class="replyContent">${reply.repCon}</div>
						      
						      <br /><hr />
					      </li>
					      
						 <!-- 댓글 삭제/수정 버튼 -->
						 <div>
						  	<button type="button" class="replyUpdateBtn" data-rno="${reply.rno}">수정</button>
						  	<button type="button" class="replyDeleteBtn" data-rno="${reply.rno}">삭제</button>
						 </div>
						 <br />
					      
					 </c:forEach>
			 	 
				  </ul>  
			 </section>
		</div>
				<!-- JQuery 사용을 위해  추가 -->
		 		<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
		 		
				<!-- 댓글 삭제/수정 버튼  - 작동 script-->
				<script>
					//댓글 수정 View
					$(".replyUpdateBtn").on("click", function(){
						location.href = "/board/replyUpdateView?bno=${view.bno}"
							+ "&rno="+$(this).attr("data-rno");
						// $(this).attr("data-rno")는 클릭 이벤트가 발생한 수정 버튼의 data-rno값을 가져오겠다는 말
					});
											
					//댓글 삭제 View
					$(".replyDeleteBtn").on("click", function(){
						location.href = "/board/replyDeleteView?bno=${view.bno}"
							+ "&rno="+$(this).attr("data-rno");
					});
				</script>
				
			  <%-- 
				  <script>
					 $(document).on("click", ".delete", function(){
					  
					  var data = {rno : $(this).attr("data-rno")};
					   
					  $.ajax({
					   url : "/board/view/deleteReply",
					   type : "post",
					   data : data,
					   success : function(result){
						   if(result == 1) {
						   	  replyList();
						   } else {
						      alert("본인의 댓글만 삭제할 수 있습니다.");     
						   }
						}
					  });
					 });
				  </script>
				  
				  <script>
				  	var bno = ${view.bno};
				  	// getJSON : 비동기식으로 제이슨(Json) 데이터를 가져온다.
				  	$.getJSON("/board/view/replyList" + "?bno=" + bno, function(data) {
				  	// 위 주소로 controller에 접속해서 데이터를 가져온다.  -> 가져온 데이터를 data에 담아서 function 실행
				  	// (return으로 vo를 통째로 받는다.(ResponseBody 사용) )
				  	
				  		var str = "";
				  		
				  		$(data).each(function() {	
				  		// data의 각 요소에 대하여 함수 각각 실행 (each 반복문)
				  		// each() 메소드는 배열 또는 객체가 가진 값을 사용해 반복문을 만들어 주는 제이쿼리 메소드			
				  			console.log(data);
				  			
				  			var repDate = new Date(this.repDate);
				  			repDate = repDate.toLocaleDateString("ko-US")
				  		/* 테이블에 저장된 날짜 데이터 형식과, 컨트롤러에서 뷰로 보낼때의 날짜 데이터 형식이 다르기 때문에 
				  			 컨트롤러에서 toLocaleDateString()를 이용해서 데이터를 1차적으로 가공한다. */
				  			
				  			// controller에 접속해서 가져온 데이터들을 조립해서 str 변수에 저장한다.
				  			str += "<li data-bno='" + this.bno + "'>"
						  		    + "<div class='userInfo'>"
							  		    + "<span class='userName'>" + this.userName + "</span>"
							  		    + "<span class='date'>" + repDate + "</span>"
						  		    + "</div>"
						  		    + "<div class='replyContent'>" + this.repCon + "</div>"
					  		    + "</li>";  
				  		});
				  		
					  	// 조립된 데이터들이 담긴 str 변수를 ol 태그에 추가한다.
					  	$("section.replyList ol").html(str);
				  	
				  	});
				  </script>
			  --%>
	</body>
</html>