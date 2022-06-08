<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
<meta charset="EUC-KR">
<title>boardList</title>
</head>
<body>
	<div class="container">
		<h1 style="color: Green;">글 목록</h1>
		<table class="table table-success">
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>글내용</th>
					<th>쓴 날짜</th>
					<th>수정 날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var="board" items="${boardList }">
						<tr>
						<td>${board.bno }</td>
						<td><a href="/board/detail?bno=${board.bno}">${board.title }</a></td>
						<td>${board.writer }</td>
						<td>${board.content }</td>
						<td>${board.regdate }</td>
						<td>${board.updatedate }</td>
						</tr>						
					</c:forEach>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-primary" href="/board/insert">글 쓰기</a>
	</div>
</body>
</html>