package kr.co.jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private static ArticleDAO instance = new ArticleDAO();
	
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	public void insertArticle(ArticleDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ARITCLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("insertArticle() error : "+e.getMessage());
		}
		
	}
	public ArticleDTO selectArticle(int no) {
		return null;
	}
	public List<ArticleDTO> selectArticles() {
		return null;
	}
	public void updateArticle(ArticleDTO dto) {}
	public void deleteArticle(int no) {}
	
	
}
