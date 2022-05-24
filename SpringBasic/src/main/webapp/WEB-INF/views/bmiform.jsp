<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
     <form action="http://localhost:8181/bmi" method="post">
    	<input type="number" name="height" placeholder="키(cm)" required/>
     	<input type="number" name="kg" placeholder="몸무게(kg)" required/>
     	<input type="submit" value="BMI 지수 확인"/>
     </form>
</body>
</html>