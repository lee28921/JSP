<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("UTF-8");
	
	String content = request.getParameter("content");
	String parent = request.getParameter("parent");
	String writer = request.getParameter("writer");
	String regip = request.getLocalAddr();
	
	ArticleDTO dto = new ArticleDTO();
	dto.setContent(content);
	dto.setParent(parent);
	dto.setWriter(writer);
	dto.setRegip(regip);
	
	ArticleDAO dao = new ArticleDAO();
	
	// 댓글 입력
	dao.insertComment(dto);
	
	// 댓글 카운트 증가
	dao.updateArticleForCommentPlus(parent);
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent);
%>