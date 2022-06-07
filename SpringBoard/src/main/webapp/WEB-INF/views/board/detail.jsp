<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>boardDetail</title>
</head>
<body>
 	${board} <br/>
 	<hr>
 	글제목 : ${board.title } <br/>
 	글쓴이 : ${board.writer } <br/>
 	글내용 : <textarea rows="20" cols="50">${board.content }</textarea>
</body>
</html>