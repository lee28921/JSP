<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user2:생성</title>
	</head>
	<body>
		<h3>user2 글생성</h3>
		<a href="/Ch10/user2/">메인</a>
		<a href="/Ch10/user2/list.do">등록</a>
		
		<form action="/Ch10/user2/register.do" method="post">
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
					<td><input type="number" name="age"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</body>
</html>