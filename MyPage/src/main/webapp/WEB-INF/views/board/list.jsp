<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/main.jsp" />
		<h3>자유게시판</h3>
		<c:if test="${user!=null}">
		<button type="button" class="btn btn-default" onclick="location.href='/write'">글쓰기</button>
		</c:if>
	<table class="table table-hover ">
		<thead>
			<tr>
				<th width=5%>번호</th>
				<th width=60%>제목</th>
				<th width=10%>작성자</th>
				<th width=20%>날짜</th>
				<th width=5%>조회수</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.boardNo}</td>
					<td><a href="board/read?boardNo=${item.boardNo}">${item.boardTitle}</a></td>
					<td>${item.boardWriter}</td>
					<td>${item.regDt}</td>
					<td>${item.boardViews}</td>
				</tr>
			</c:forEach>
			</tbody>
	</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.js"></script>
</body>
</html>