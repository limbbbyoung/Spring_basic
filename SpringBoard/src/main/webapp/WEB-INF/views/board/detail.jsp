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
 	������ : ${board.title } <br/>
 	�۾��� : ${board.writer } <br/>
 	�۳��� : <textarea rows="20" cols="50">${board.content }</textarea>
</body>
</html>