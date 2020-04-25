<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
	
		<h1>
			회원가입
		</h1>
		
		<P>  The time on the server is ${serverTime}. </P>
		
		 <title>회원가입</title> 
		 
		 <!-- Ajax 사용을 위해 JQuesy 추가 -->
		 <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
		 
	</head>
	<body>
		<!-- autocomplete="off"로 설정하면, 자동완성 기능이 꺼진다. -->
		<form role="form" method="post" autocomplete="off">
		<!-- role="form"에 의해서, 버튼 클릭시, 입력한 vo 정보들이 컨트롤러에 들어간다. -->
			 <p>
				  <label for="userId">아이디</label>
				  <input type="text" id="userId" name="userId" />
				  
				  <!-- 중복확인 버튼을 누르면 아래(Ajax) idCheck 함수 호출 -->
				  <button type="button" class="idCheck">중복확인</button>
			 </p>  
		 	 <p class="result">
		 	 	<span class="msg">[아이디 중복을 확인해주세요.]</span>
		     </p>
			 <p>
				  <label for="userPass">패스워드</label>
				  <input type="password" id="userPass" name="userPass" />
			 </p>
			 <p>
				  <label for="userName">닉네임</label>
				  <input type="text" id="userName" name="userName" />
			 </p>
			 <p>
			 	<button type="submit" id="submit" disabled="disabled">가입</button>  
			 </p>
		</form>
	</body>
	
	<script>
		$(".idCheck").click(function(){ // idCheck에 해당하는 버튼이 클릭되면,
			 var query = {userId : $("#userId").val()}; // 아이디가 userId인 것의 값(.val())을 query 변수에 저장한다.
			 // var의 타입은 자동으로 JSON 형식의 변수가 된다.
		
			 // ajax 시작
			 $.ajax({
				  url : "/member/idCheck", // 위 정보가 전송될 주소(컨트롤러에 생성한 메서드의 매핑 주소 입력)
				  type : "post",	// 전송방식
				  data : query, 	// 전송할 값->변수
				  
				  // 위 과정이 정상진행시, 아래 successCall 메소드 호출
				  success : successCall,
				  // error시,,
				  error : errorCall
			 });  
			 
			 function successCall(data) { // succes시 호출할 function() 작성
				   if(data == 1) {
					   // result 클래스의 msg 속성의 text 속성을 변경 ,,,
				    	$(".result .msg").text("[사용 불가]"); // 선택한 요소 안의 내용을 가져옵니다. 태그는 가져오지 않습니다. 
				    	$(".result .msg").attr("style", "color:#f00"); 
				    	//  ".result .msg" 요소에 "style" 속성을 추가하고 속성의 값은 "color:#f00" 로 합니다.
						alert("사용불가");
				    	// 가입 버튼 lock
				    	$("#submit").attr("disabled", "disabled");
				   } 
				   else if(data == 0){
				  		$(".result .msg").text("[사용 가능]");
				   		$(".result .msg").attr("style", "color:#00f");
				   		// ,,,
				   		alert("사용가능");
				   		// 가입 버튼 lock 해제
				    	$("#submit").removeAttr("disabled");
				   }
				   else{
					   alert("시스템 오류 발생");
				   }
			  }
			 function errorCall(){
				   alert("데이터 전송 실패");
			 }
			 // ajax 끝
			 
		});
		
		// 아이디 입력창에 무언가 입력되면, "가입" 버튼 상태 수정( 꼼수 방지 )
		$("#userId").keyup(function(){
			 $(".result .msg").text("[아이디 중복을 확인해주세요.]");
			 $(".result .msg").attr("style", "color:#000");
			 
			 $("#submit").attr("disabled", "disabled");
		});
	</script>
	
	<jsp:include page="../side/footer.jsp" />
	
</html>