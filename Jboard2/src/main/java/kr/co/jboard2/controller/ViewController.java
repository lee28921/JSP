package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = -2120022050925682570L;

	private ArticleService service = ArticleService.INSIANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ViewController doGet...1");
		
		String no = req.getParameter("no");
		
		// 게시글 보기
		ArticleDTO article = service.selectArticle(no);
		req.setAttribute("article", article);
		
		logger.debug("Article no : "+article.getNo());
		logger.debug("Article file : "+article.getFile());
		
		// 댓글 조회
		List<ArticleDTO> comments = service.selectComments(no);
		req.setAttribute("comments", comments);
		
		logger.debug("comments Empty : "+comments.isEmpty());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ViewController doPost...1");
		
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		logger.debug("parent : "+parent);
		logger.debug("content : "+content);
		logger.debug("writer : "+writer);
		logger.debug("regip : "+regip);
		
		// 댓글 저장
		ArticleDTO dto = new ArticleDTO();
		dto.setContent(content);
		dto.setParent(parent);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		service.insertComment(dto);
		
		// 게시글 댓글 갯수 추가
		service.updateArticleForCommentPlus(parent);
		
		resp.sendRedirect("/Jboard2/view.do?no="+parent);
		
	}
}
