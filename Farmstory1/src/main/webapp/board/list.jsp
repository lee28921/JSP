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
	
	// 목록 영역
	pageContext.include("./_aside"+group+".jsp");
	
	// 페이지 관련 변수
	int start = 0;
	int currentPage = 1;

	// 현재 페이지 계산
	if(pg != null){
		currentPage = Integer.parseInt(pg);
		
	}
	// Limit 시작값 계산
	start = (currentPage -1) * 10;

	
	// 글 조회
	ArticleDAO dao = new ArticleDAO();
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
			                <td><%= article.getNo() %></td>
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
			       <a href="#" class="prev">이전</a>
			       
			       <a href="#"></a>
			       
			       <a href="#" class="next">다음</a>
			   </div>
			
			   <!-- 글쓰기 버튼 -->
			    <a href="./write.jsp?group=<%= group %>&cate=<%= cate %>" class="btnWrite">글쓰기</a>
			</section>
			<!-- 내용 끝 -->
			
		</article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>