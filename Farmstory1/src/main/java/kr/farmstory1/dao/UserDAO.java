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
	public UserDTO selectUser(String uid,String pass) {
		UserDTO user = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
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
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
