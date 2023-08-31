package kr.co.jboard2.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 992090960044622875L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSIANCE;
	private FileService fService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MultipartRequest mr = aService.uploadFile(req);
		
		// 폼 데이터 수신
		String title	= mr.getParameter("title");
		String content	= mr.getParameter("content");
		String writer	= mr.getParameter("writer");
		String oName	= mr.getOriginalFileName("file");
		String regip	= req.getRemoteAddr();
		
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("writer : "+writer);
		logger.debug("file : "+oName);
		logger.debug("regip : "+regip);
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		logger.debug("file Count : "+dto.getFile());
		
		// 글 Insert
		int no = aService.insertArticle(dto); // 게시글 먼저 저장
		
		logger.debug("file ArticleNo :"+no);
		
		// 파일명 수정
		if(oName != null) {
		
		String sName = aService.renameToFile(req, oName);
			
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no); // 저장된 게시글 조회 후 file 테이블에 저장
			fileDto.setOfile(oName);
			fileDto.setSfile(sName);
			
			fService.insertFile(fileDto);
		}
		
		
		// 리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}
}
