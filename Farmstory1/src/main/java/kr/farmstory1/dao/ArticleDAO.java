package kr.farmstory1.dao;


import kr.farmstory.dto.ArticleDTO;
import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;

public class ArticleDAO extends DBHelper{
	
		// 기본 CRUD 
		public void insertArticle(ArticleDTO dto) { // 글작성
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
				psmt.setString(1, dto.getCate());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getWriter());
				psmt.setString(5, dto.getRegip());
				psmt.executeUpdate();
				
				close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
