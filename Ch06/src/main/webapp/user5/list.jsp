<%@page import="vo.User5VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.catalina.LifecycleState"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;cherset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	List<User5VO> users = new ArrayList<>();
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host,user,pass);
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user5`");
		
		while(rs.next()) {
			User5VO vo = new User5VO();		
			vo.setName(rs.getString(1));
			vo.setBirth(rs.getString(2));
			vo.setAge(rs.getInt(3));
			vo.setAddress(rs.getString(4));
			vo.setHp(rs.getString(5));
			
			users.add(vo);
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user5 목록</title>
	</head>
	<body>
		<h3>user5 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user5/register.jsp">User5 등록</a>

		<table border=1>
			<tr>
				<th>이름</th>
				<th>생일</th>
				<th>나이</th>
				<th>주소</th>
				<th>휴대폰</th>
				<th>관리</th>
			</tr>
			<% for(User5VO vo : users) { %>
			<tr>
				<td><%= vo.getName() %></td>
				<td><%= vo.getBirth() %></td>
				<td><%= vo.getAge() %></td>
				<td><%= vo.getAddress() %></td>
				<td><%= vo.getHp() %></td>
				<td>
					<a href="/Ch06/user5/modify.jsp?hp=<%= vo.getHp() %>">수정</a>
					<a href="#">삭제</a>
				</td>
			</tr>
			<% } %>
		</table>		
	</body>
</html>