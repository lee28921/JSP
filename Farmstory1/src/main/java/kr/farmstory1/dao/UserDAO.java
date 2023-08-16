package kr.farmstory1.dao;

import java.util.List;

import kr.farmstory.dto.UserDTO;
import kr.farmstory1.db.DBHelper;

public class UserDAO extends DBHelper{
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	private UserDAO() {}
	
	public void insertUser(UserDTO dto) {}
	public UserDTO selectUser(String uid) {
		return null;
	}
	public List<UserDTO> selectUsers(UserDTO dto) {
		return null;
	}
	public void updateUser(String uid) {}
	public void deleteUser(String uid) {}
	
}
