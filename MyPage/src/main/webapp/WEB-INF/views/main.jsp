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
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="/resources/bootstrap/js/bootstrap.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">MyPage</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">

			<div class="navbar-nav" style="width: 15%;">
				<a class="nav-link active" href="/">공지사항</a> <a
					class="nav-link active" href="/board/page?num=1">자유게시판</a>
			</div>

			<div class="container-fluid">
				<c:if test="${user==null}">
					<div class="right">
						<a href="/user/login">로그인</a> <a href="/user/join">회원가입</a>
					</div>
				</c:if>

				<c:if test="${user!=null}">
					<div class="right">
						<%-- <c:if test="${user.adminCHK==1 }">
							<p>${user.userNM}관리자가접속했습니다.</p>
						</c:if> --%>
						<a href="/user/logout">로그아웃</a>
						<p>${user.userNM}님환영합니다.</p>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</nav>
</head>
<body>

<!-- 공지사항, 자유게시판 위치 -->
</body>
</html>