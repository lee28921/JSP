package kr.co.jboard2.dao;

import java.util.ArrayList;
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
	
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // Transaction 시작
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate(); // 게시글을 저장
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO); // 해당 게시글의 글번호 조회
			
			conn.commit(); // 작업확정(psmt,stmt 동시 실행)
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			
		} catch (Exception e) {
			logger.error("insertArticle() error : "+e.getMessage());
		}
		
		return no;
		
	}
	public ArticleDTO selectArticle(String no) {
		
		ArticleDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new ArticleDTO();
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
				
			}
			close();
		}catch(Exception e) {
			logger.error("selectArticle() error : "+e.getMessage());
		}
		
		return dto;
	}
	public List<ArticleDTO> selectArticles(int start) {
		
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1, start);
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
				dto.setNick(rs.getString(12));
				
				articles.add(dto);
			}
			close();
		} catch(Exception e) {
			logger.error("selectArticles() error : "+e.getMessage());
		}
		
		return articles;
	}
	public void updateArticle(ArticleDTO dto) {}
	public void deleteArticle(int no) {}
	
	// 추가
	public int selectCountTotal() {
		int total = 0;

		try {
		conn = getConnection();
		psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
		rs = psmt.executeQuery();

		if(rs.next()) {
			total = rs.getInt(1);
		}
		close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}
	
}
