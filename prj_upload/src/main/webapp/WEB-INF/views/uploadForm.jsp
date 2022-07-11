<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="uploadFormAction" method="post"
							enctype="multipart/form-data"> <!-- enctype : 인코딩 타입 
															=> 파일을 어떻게 보내는지에 대한 것을 처리
															multipart, 여러개의 파일을 보낸다는 의미 -->
	<input type="file" name="uploadFile" multiple>
	<button>Submit</button> <!-- form 내부에 input type="submit"이 따로 존재하지 않는다면 자동으로 
								submit의 성질을 가지게 됨 -->			
	</form>
</body>
</html>