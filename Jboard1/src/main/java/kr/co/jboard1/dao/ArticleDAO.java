package kr.co.jboard1.dao;

import java.util.List;

import kr.co.jboard1.db.DBHelper;
import kr.co.jboard1.db.SQL;
import kr.co.jboard1.vo.ArticleVO;

public class ArticleDAO extends DBHelper {

	
	
	
	// 기본 CRUD 
	public void insertArtcle(ArticleVO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ARITCLE);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setString(4, vo.getRegip());
			psmt.executeUpdate();
			
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArticleVO selectArtcle(int no) {
		return null;
	}
	public List<ArticleVO> selectArtcles() {
		return null;
	}
	public void updateArtcle(ArticleVO vo) {
		
	}
	public void deleteArtcle(int no) {
		
	}
	
}
