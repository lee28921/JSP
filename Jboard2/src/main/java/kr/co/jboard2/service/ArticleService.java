package kr.co.jboard2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;

public enum ArticleService {

	INSIANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
	
	// 추가
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	
	public void insertComment(ArticleDTO dto) {
		dao.insertComment(dto);
	}
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	// 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
		
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		// 파일명 수정
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+oName); // 저장된 파일의 객체
		File f2 = new File(path+"/"+sName); // 가상의 파일 객체
		
		// 파일명 수정
		f1.renameTo(f2);
		
		return sName;
	}
	
	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile() error : "+e.getMessage());
		}
		
		return mr;
	}
	
	// 파일 다운로드
	public void downloadFile() {
		
	}
	
}
