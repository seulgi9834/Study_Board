<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script src="/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/main.jsp" />
	<div class="title">
		<h2>자유게시판 글쓰기</h2>
	</div>
	<form action="/board/regist" method="post">
		<label for="boardTitle">제목</label> <input id="boardTitle"
			name="boardTitle" type="text"><br> <label
			for="boardWriter">작성자</label> <input id="boardWriter"
			name="boardWriter" type="text"><br>
		<p>내용</p>
		<label for="content"></label>
		<textarea style="width: 500px; height: 500px" id="boardContent"
			name="boardContent"></textarea>
		<script>
			var ckeditor_config = {
				resize_enaleb : false,
				enterMode : CKEDITOR.ENTER_BR,
				shiftEnterMode : CKEDITOR.ENTER_P,
				filebrowserUploadUrl : "/board/regist"
			};

			CKEDITOR.replace("boardContent", ckeditor_config);
		</script>
		<br> <input type="submit" value="글쓰기">
	</form>
</body>
</html>