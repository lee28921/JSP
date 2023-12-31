package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 401591197536074540L;

	private ArticleService service = ArticleService.INSIANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String kind = req.getParameter("kind");
		String no = req.getParameter("no");
	
		int result = 0;
		
		switch(kind) {
		case "REMOVE":
			
			result = service.deleteComment(no);
			break;
		}
		
		
		logger.debug("kind : "+kind);
		logger.debug("result : "+result);
		
		// Json 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		
		int result = service.insertComment(dto);
		
		
		
		// 게시글 댓글 갯수 추가
		// service.updateArticleForCommentPlus(parent);
		
		// 리다이렉트(폼 전송)
		//resp.sendRedirect("/Jboard2/view.do?no="+parent);
		
		
		// Json 출력 (AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
	}
}
