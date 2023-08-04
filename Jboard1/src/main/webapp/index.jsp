<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 로그인 여부를 확인
	UserVO sessUser = (UserVO) session.getAttribute("sessUser");
	

	if(sessUser == null){
		// forward는 시스템 내에서 자원 이동하기 때문에 서버 경로 대신 상대경로 지정
		pageContext.forward("./user/login.jsp");
	}else{
		pageContext.forward("./list.jsp");
	}
%>