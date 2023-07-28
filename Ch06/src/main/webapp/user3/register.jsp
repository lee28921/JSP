<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User3 register</title>
	</head>
	<body>
	<h3>User3 생성</h3>
	<a href="/Ch06/1_JDBC.jsp">처음으로</a>
	<a href="/Ch06/user3/list.jsp">User3 목록</a>
	
	<form action="/Ch06/user3/registerProc.jsp">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid"></td>			
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>			
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp"></td>			
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="text" name="age"></td>			
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="전송"></td>			
			</tr>
		</table>
	</form>
	
	</body>
</html>