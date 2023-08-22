<%@page import="kr.farmstory.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	
	ProductDAO dao = new ProductDAO();
	
	// 페이지 관련 변수
	int start = 0;
	int currentPage = 1;
	int total = dao.selectCountProductsTotal("0");
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 현재 페이지 계산
	if(pg != null){
		currentPage = Integer.parseInt(pg);
	}
	// Limit 시작값 계산
	start = (currentPage -1) * 10;
	
	// 페이지 그룹계산 - 페이지 네비게이션
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1; 
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	// 페이지 시작번호 계산
	pageStartNum = total - start;
	
	List<ProductDTO> products = dao.selectProducts("0", start);
%>
<main>
    <%@ include file="./_aside.jsp" %>
    <section id="productList">
        <nav>
            <h3>상품목록</h3>
        </nav>

        <article>

            <table border="0">
                <tr>
                    <th><input type="checkbox" name="all"/></th>
                    <th>사진</th>
                    <th>상품번호</th>
                    <th>상품명</th>
                    <th>구분</th>
                    <th>가격</th>
                    <th>재고</th>
                    <th>등록일</th>
                </tr>
                <% for(ProductDTO product : products){ %>
                <tr>
                    <td><input type="checkbox" name=""/></td>
                    <td><img src="/Farmstory1/thumb/<%= product.getThumb1() %>" class="thumb" alt="사진1"></td>
                    <td><%= product.getpNo() %></td>
                    <td><%= product.getpName() %></td>
                    <td><%= product.getType() %></td>
                    <td><%= product.getPriceWithComma() %>원</td>
                    <td><%= product.getStock() %></td>
                    <td><%= product.getRdate() %></td>
                </tr>
                <% } %>
            </table>

            <p>
                <a href="#" class="productDelete">선택삭제</a>
                <a href="./productRegister.jsp" class="productRegister">상품등록</a>
            </p>
            
            <p class="paging">
            	<% if(pageGroupStart > 1) { %>
                <a href="./productList.jsp?pg=<%= pageGroupStart - 1 %>"><</a>
                <% } %>
                <% for(int i=pageGroupStart; i<=pageGroupEnd; i++) { %>
                <a href="./productList.jsp?pg=<%= i %>" class="num <%= (currentPage == i)?"on":"" %>">[<%= i %>]</a>
                <% } %>
                <% if(pageGroupEnd < lastPageNum){ %>
                <a href="./productList.jsp?pg=<%= pageGroupEnd + 1 %>">></a>
                <% } %>
            </p>

        </article>

        
    </section>
</main>
<%@ include file="./_footer.jsp" %>