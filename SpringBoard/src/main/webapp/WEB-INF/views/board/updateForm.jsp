<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
<style>
	.container { width: 400px;
	  			margin-top: 35px; }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${board }
	<div class="container">	
	 <form action="/board/update" method="post">
	     글제목 : <input class="form-control" type="text" name="title" value="${board.title }" required><br/>
	     작성자 : <input class="form-control" type="text" name= "writer" value="${board.writer}" required><br/>
	     <p>글내용 : </p> <textarea class="form-control" cols="50" rows="12" name="content" required>${board.content}</textarea><br/>
	     <input type="hidden" name="bno" value="${board.bno }">
	     <input type="hidden" name="page" value="${param.page }">
	     <input type="hidden" name="searchType" value="${param.searchType}">
	     <input type="hidden" name="keyword" value="${param.keyword}">
	     <button type="submit" class="btn btn-success" >글 수정하기</button>
     </form>
    </div>
</body>
</html>