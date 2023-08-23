<%@page import="kr.farmstory.dto.OrderDTO"%>
<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	// 주문상품 정보
	String pNo = request.getParameter("pNo");
	String delivery = request.getParameter("delivery");
	String count = request.getParameter("count");
	String price = request.getParameter("price");
	String total = request.getParameter("total");
	String uid = request.getParameter("uid");
	
	// 주문정보
	String buyer = request.getParameter("buyer");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String etc = request.getParameter("etc");
	
	OrderDTO dto = new OrderDTO();
	dto.setOrderProduct(pNo);
	dto.setOrderCount(count);
	dto.setOrderDelivery(delivery);
	dto.setOrderPrice(price);
	dto.setOrderTotal(total);
	dto.setOrderUser(uid);
	dto.setReceiver(buyer);
	dto.setHp(hp);
	dto.setZip(zip);
	dto.setAddr1(addr1);
	dto.setAddr2(addr2);
	dto.setOrderEtc(etc);
	
	OrderDAO dao = new OrderDAO();
	dao.insertOrder(dto);
	
	response.sendRedirect("/Farmstory1/market/list.jsp?type=0");
%>