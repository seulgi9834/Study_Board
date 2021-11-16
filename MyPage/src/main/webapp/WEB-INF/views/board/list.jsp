<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/main.jsp" />
	<div class="title">
		<h2>자유게시판</h2>
		<button type="button" onclick="location.href='board/write'">글쓰기</button>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th width=5%>번호</th>
				<th width=60%>제목</th>
				<th width=10%>작성자</th>
				<th width=15%>날짜</th>
				<th width=10%>조회수</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.boardNo}</td>
					<td><a href="board/read?boardNo=${item.boardNo}">${item.boardTitle}</a></td>
					<td>${item.boardWriter}</td>
					<td>${item.regDt}</td>
					<td>${item.boardViews}</td>
				</tr>
			</c:forEach>
	</table>

</body>
</html>