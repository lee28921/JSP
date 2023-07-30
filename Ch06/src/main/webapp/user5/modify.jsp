<%@page import="vo.User5VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;cherset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String hp = request.getParameter("hp");
	
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	User5VO vo = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host,user,pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user5` WHERE `hp`=?");
		psmt.setString(1, hp);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			vo = new User5VO();
			vo.setName(rs.getString(1));
			vo.setBirth(rs.getString(2));
			vo.setAge(rs.getInt(3));
			vo.setAddress(rs.getString(4));
			vo.setHp(rs.getString(5));
			
		}
		rs.close();
		psmt.close();
		conn.close();
		
	} catch(Exception e){
		e.printStackTrace();
	}
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User5 수정</title>
	</head>
	<body>
		<form action="/Ch06/user5/modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value=<%= vo.getName() %>></td>
				</tr>
				<tr>
					<td>생일</td>
					<td><input type="date" name="birth" value=<%= vo.getBirth() %>></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value=<%= vo.getAge() %>></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value=<%= vo.getAddress() %>></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value=<%= vo.getHp() %>></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="전송"></td>
				</tr>
			</table>
		</form>
	</body>
</html>