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
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = -2120022050925682570L;

	private FileService FService = FileService.INSIANCE;
	private ArticleService AService = ArticleService.INSIANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ViewController doGet...1");
		
		String no = req.getParameter("no");
		
		// 게시글 보기
		ArticleDTO article = AService.selectArticle(no);
		req.setAttribute("article", article);
		
		logger.debug("Article no : "+article.getNo());
		logger.debug("Article file : "+article.getFile());
		logger.debug("Article hit : "+article.getHit());
		
		// 업로드된 파일 출력
		FileDTO fileDto = FService.selectFile(no);
		
		// 댓글 조회
		List<ArticleDTO> comments = AService.selectComments(no);
		req.setAttribute("comments", comments);
		
		logger.debug("comments Empty : "+comments.isEmpty());
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
}
