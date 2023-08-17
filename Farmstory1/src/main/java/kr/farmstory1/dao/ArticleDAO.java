package kr.farmstory1.dao;


import java.util.ArrayList;
import java.util.List;

import kr.farmstory.dto.ArticleDTO;
import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;

public class ArticleDAO extends DBHelper {
	
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
		
		public List<ArticleDTO> selectArticles(String cate, int start) {
			List<ArticleDTO> articles = new ArrayList<>();
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					ArticleDTO dto = new ArticleDTO();
					dto.setNo(rs.getInt(1));
					dto.setParent(rs.getInt(2));
					dto.setComment(rs.getInt(3));
					dto.setCate(rs.getString(4));
					dto.setTitle(rs.getString(5));
					dto.setContent(rs.getString(6));
					dto.setFile(rs.getInt(7));
					dto.setHit(rs.getInt(8));
					dto.setWriter(rs.getString(9));
					dto.setRegip(rs.getString(10));
					dto.setRdate(rs.getString(11));
					
					articles.add(dto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return articles;
		}
}
