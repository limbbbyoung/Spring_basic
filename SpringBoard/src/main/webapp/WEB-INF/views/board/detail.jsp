<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>boardDetail</title>
</head>
<body>
 	${board} <br/>
 	<hr>
 	글제목 : ${board.title } <br/>
 	글쓴이 : ${board.writer } <br/>
 	글내용 : <textarea rows="20" cols="50">${board.content }</textarea>
 	<form action="/board/delete" method="post">
 	<input type="hidden" value="${board.bno }" name="bno">
 	<button type="submit">글 삭제하기</button>
 	</form>
 	<form action="/board/updateForm" method="post">
 	<input type="hidden" value="${board.bno }" name="bno">
 	<button type="submit">글 수정하기</button>
 	</form>
 	<a class="btn btn-primary" href="/board/list">글 목록</a>
</body>
</html>