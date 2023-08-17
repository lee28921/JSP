<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@page import="kr.farmstory.dto.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	// 서브페이지, 카테고리
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	
	ArticleDAO dao = new ArticleDAO();
	
	// 목록 영역
	pageContext.include("./_aside"+group+".jsp");
	
	// 페이지 관련 변수
	int start = 0;
	int currentPage = 1;
	int total = 0; 
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
	
	// 전체 게시물 갯수 조회
	total = dao.selectCountTotal(cate);

	// 페이지 번호 계산
	if(total % 10 == 0){
		lastPageNum = (total / 10);
	}else{
		lastPageNum = (total / 10) + 1; 
	}

	// 페이지 그룹계산 - 페이지 네비게이션
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);

		//그룹 시작 번호가 1, 11, 21 순으로 보이게 하기
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1; 
	
		//그룹 마지막 번호가 10, 20, 30 순으로 보이게 하기
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	// 페이지 시작번호 계산
	pageStartNum = total - start;
	
	// 글 조회
	List<ArticleDTO> articles = dao.selectArticles(cate,start);
	
%>

			<section class="list">
			    <h3>글목록</h3>
			    <article>
			        <table border="0">
			            <tr>
			                <th>번호</th>
			                <th>제목</th>
			                <th>글쓴이</th>
			                <th>날짜</th>
			                <th>조회</th>
			            </tr>
			            <% for(ArticleDTO article : articles){ %>
			            <tr>
			                <td><%= pageStartNum-- %></td>
			                <td><a href="./view.jsp?group=<%= group %>&cate=<%= cate %>"><%= article.getTitle() %></a>&nbsp;[<%= article.getComment() %>]</td>
			                <td><%= article.getNick() %></td>
			                <td><%= article.getRdate() %></td>
			                <td><%= article.getHit() %></td>
			            </tr>
			            <% } %>
			        </table>
			    </article>
			
			    <!-- 페이지 네비게이션 -->
			   <div class="paging">
			   		<% if(pageGroupStart > 1) { %>
			   		<a href="./list.jsp?pg=<%= pageGroupStart - 1 %>&group=<%= group %>&cate=<%= cate %>" class="prev">이전</a>
			   		<% } %>
			   		
					<% for(int i=pageGroupStart; i<=pageGroupEnd; i++) { %>
					<a href="./list.jsp?pg=<%= i %>&group=<%= group %>&cate=<%= cate %>" class="num <%= (currentPage == i)?"current":"" %>"><%= i %></a>
					<% } %>
			       
			       <% if(pageGroupEnd < lastPageNum){ %>
			       <a href="./list.jsp?pg=<%= pageGroupEnd + 1 %>&group=<%= group %>&cate=<%= cate %>" class="next">다음</a>
			       <% } %>
			   </div>
			
			   <!-- 글쓰기 버튼 -->
			    <a href="./write.jsp?group=<%= group %>&cate=<%= cate %>" class="btnWrite">글쓰기</a>
			</section>
			<!-- 내용 끝 -->
			
		</article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>