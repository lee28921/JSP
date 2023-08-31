package kr.co.jboard2.service;

import java.util.List;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;

public enum ArticleService {

	INSIANCE;
	
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public void insertArticle(ArticleDTO dto) {
		dao.insertArticle(dto);
	}
	
	public ArticleDTO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles() {
		return dao.selectArticles();
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
}
