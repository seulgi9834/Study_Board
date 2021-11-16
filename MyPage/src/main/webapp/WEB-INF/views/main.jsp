<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<!--CSS-->
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="right">
		<a href="#">로그인</a> <a href="#">회원가입</a>
	</div><br>
	<div class="index">
		<ul>
			<li><a href="#">메인화면</a></li>
			<li><a href="#">공지사항</a></li>
			<li><a href="board">자유게시판</a></li>
		</ul>
	</div>

</body>
</html>