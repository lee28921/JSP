<%@page import="sub1.Account"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>4_Class</title>
		<!--
			날짜: 2023/07/24
			이름: 이지민
			내용: JSP 스크립트릿 클래스 실습하기 
		 -->
	</head>
	<body>
		<h3>4.클래스</h3>
		
		<%
			Account kb = new Account("국민은행","101-12-1001","김유신",10000);
			kb.depsoit(30000);
			kb.withdraw(5000);
			kb.show(out); //바로 쓸 수 있는 객체
			
			Account wr = new Account("우리은행","101-11-1001","김춘추",20000);
			wr.depsoit(30000);
			wr.withdraw(5000);
			wr.show(out);
		%>
		
	</body>
</html>