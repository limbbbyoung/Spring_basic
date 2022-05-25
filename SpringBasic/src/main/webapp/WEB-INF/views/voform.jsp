<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	 <form action="http://localhost:8181/getVO" method="get">
      	<input type="text" name="name" placeholder="이름" required/><br/>
    	<input type="number" name="age" placeholder="나이" required/><br/>
     	<input type="number" name="level" placeholder="레벨" required/><br/>
     	<input type="text" name="skill" placeholder="능력" required/><br/>
     	<input type="submit" value="입력 정보 확인"/>
     </form>
</body>
</html>