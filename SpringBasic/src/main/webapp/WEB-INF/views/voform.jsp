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
      	<input type="text" name="name" placeholder="�̸�" required/><br/>
    	<input type="number" name="age" placeholder="����" required/><br/>
     	<input type="number" name="level" placeholder="����" required/><br/>
     	<input type="text" name="skill" placeholder="�ɷ�" required/><br/>
     	<input type="submit" value="�Է� ���� Ȯ��"/>
     </form>
</body>
</html>