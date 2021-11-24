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
<c:if test="${user==null}">
	<div class="right">
		<a href="/user/login">로그인</a> <a href="/user/join">회원가입</a>
	</div><br>
	</c:if>

<c:if test="${user!=null}">
	<div class="right">
		<c:if test="${user.adminCHK==1 }">
		<p>${user.userNM}관리자가 접속했습니다.</p></c:if>

		<p>${user.userNM}님 환영합니다.</p> <a href="/user/logout">로그아웃</a>
	</div><br>
	</c:if>

	<div class="index">
		<ul>
			<li><a href="/">메인화면</a></li>
			<li><a href="/">공지사항</a></li>
			<li><a href="/board/page?num=1">자유게시판</a></li>
		</ul>
	</div>
</body>
</html>