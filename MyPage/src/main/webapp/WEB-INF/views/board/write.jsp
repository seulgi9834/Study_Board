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
	<form action="/board/regist" method="post"
		enctype="multipart/form-data">
		<label for="boardTitle">제목</label> <input id="boardTitle"
			name="boardTitle" type="text"><br>
		<input type="hidden" name="boardWriter" value="${user.userNM}" readonly="readonly"/>
		<p>내용</p>
		<label for="content"></label>
		<textarea style="width: 500px; height: 500px" id="boardContent"
			name="boardContent"></textarea>
<script>CKEDITOR.replace('boardContent',{filebrowserUploadUrl:'/board/imageUpload'});</script>

		<br> <input type="submit" value="글쓰기">
	</form>

</body>
</html>