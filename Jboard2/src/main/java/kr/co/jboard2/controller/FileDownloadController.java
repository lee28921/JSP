package kr.co.jboard2.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/fileDownload.do")
public class FileDownloadController extends HttpServlet{

	private static final long serialVersionUID = 3454011666393788527L;
	
	private ArticleService AService = ArticleService.INSIANCE;
	private FileService FService = FileService.INSIANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 수신
		String fno = req.getParameter("fno");
		
		logger.debug("fno : "+fno);
		
		// 파일 조회
		FileDTO dto = FService.selectFile(fno);
		logger.debug(dto.toString());
		
		// 파일 다운로드
		AService.downloadFile(req, resp, dto);
	}
}
