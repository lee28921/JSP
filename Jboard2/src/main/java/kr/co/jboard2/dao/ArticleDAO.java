package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

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
			
			psmt2 = conn.prepareStatement(SQL.UPDATE_ARTICLE_HIT_PLUS);
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			psmt2.setString(1, no);
			rs = psmt.executeQuery();
			psmt2.executeUpdate();
			
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
				// 파일 정보
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOfile(rs.getString(14));
				fileDto.setSfile(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				dto.setFileDto(fileDto);
				
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectArticle() error : "+e.getMessage());
		}
		
		return dto;
	}
	public List<ArticleDTO> selectArticles(int start, String search) {
		
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
				psmt.setInt(2, start);
			}
			
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
	public void deleteArticle(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("deleteArticles() error : "+e.getMessage());
		}
	}
	
	// 추가
	public int selectCountTotal(String search) {
		int total = 0;

		try {
		conn = getConnection();
		if(search == null) { // 검색 키워드가 없다면
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
		} else {
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
			psmt.setString(1, "%"+search+"%");
		}
		
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
	
	public int insertComment(ArticleDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			
			result = psmt.executeUpdate();
			close();
			
		} catch(Exception e) {
			logger.error("insertComment() error : "+e.getMessage());
		}
		
		return result;
		
	}
	
	public List<ArticleDTO> selectComments(String parent) {
		
		List<ArticleDTO> comments = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
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
				
				comments.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectComments() error : "+e.getMessage());
		}
		return comments;
	}
	
	public void updateArticleForCommentPlus(String no) {
		try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
				psmt.setString(1, no);
				psmt.executeUpdate();
				close();
			} catch (Exception e) {
				logger.error("updateArticleCommentPlus() error : "+e.getMessage());
			}
	}
	
	public int deleteComment(String no) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMINT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			
			close();
			
		} catch(Exception e) {
			logger.error("deleteComment() error : "+e.getMessage());
		}
		return result;
	}
	
	
}
