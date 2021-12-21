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
<div class="layer">
	<form action="/board/regist" method="post"
		enctype="multipart/form-data">
<div class="input-group">
<span class="input-group-text">제목</span>
  <input id="boardTitle" name="boardTitle" type="text" class="form-control" placeholder="">
  </div>
		<input type="hidden" name="boardWriter" value="${user.userNM}" readonly="readonly"/>
		<label for="content"></label>
		<textarea style="width: 500px; height: 500px" id="boardContent"
			name="boardContent"></textarea>



<script>CKEDITOR.replace('boardContent',{filebrowserUploadUrl:'/board/imageUpload'});</script>

		<br> <input type="submit" value="글쓰기">
	</form>
</div>
</body>
</html>