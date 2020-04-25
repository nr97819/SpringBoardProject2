<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 작성</title>
		
		<!-- CK Editor 경로 설정 -->
		<script src="/resources/ckeditor/ckeditor.js"></script>
		
		<h2>게시글 작성</h2>
	</head>
	
	<body>
		<form action="" method="post">
			<div class="inputArea">
				<label>제목</label> <br />
				<input type="text" name="title" /> <br /> <hr />
			</div>
				
			<div class="inputArea">
				<!-- 로그인한 사용자의 이름을 불러와서
					작성자 란에 자동으로 삽입해준다.
					(*작성자명을 수정하지 못하도록 readonly(읽기 전용) 속성을 활성화시킨다.) -->
				<label>작성자</label> <br />
				<input type="text" name="writer" value="${member.userName}" readonly="readonly" /> <br /> <hr />
				<!-- 세션은 다른 메서드에 정보를 넘겨주지 않아도 그 값을 사용할 수 있습니다. ex. (${member.userName} 어디서든 사용 -->
			</div>
			
			<div class="inputArea">
				<label>내용</label> <br />
				<textarea rows="5" cols="50" name="content"></textarea> <br /> <hr />
			</div>
			
			<!-- CK Editor 사용 -->
			<script>
				// Json형태의 변수인 ckeditor_config를 선언및 설정
				 var ckeditor_config = {
				   resize_enaleb : false,
				   enterMode : CKEDITOR.ENTER_BR,
				   shiftEnterMode : CKEDITOR.ENTER_P,
				   filebrowserUploadUrl : "/admin/board/ckUpload"
				   // 파일을 업로드한 경우, 설정한 URL로 전송 (controller에 추가)
				 };
				
				 // 텍스트에어리어를 CK에디터로 교체 (name 속성 사용)
				 CKEDITOR.replace("content", ckeditor_config);
			</script>
			
			<button type="submit">저장</button>
		</form>
	</body>
	
	<jsp:include page="../side/footer.jsp" />
	
</html>