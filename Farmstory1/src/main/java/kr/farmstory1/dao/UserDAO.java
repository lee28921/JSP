package kr.farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import kr.farmstory.dto.TermsDTO;
import kr.farmstory.dto.UserDTO;
import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;

public class UserDAO extends DBHelper{
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	
	private UserDAO() {}
	
	// CRUD
	public void insertUser(UserDTO dto) {}
	public UserDTO selectUser(String uid) {
		return null;
	}
	public List<UserDTO> selectUsers(UserDTO dto) {
		return null;
	}
	public void updateUser(String uid) {}
	public void deleteUser(String uid) {}
	
	// 추가
	public TermsDTO selectTerms() {
		TermsDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_TERMS);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto = new TermsDTO();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
