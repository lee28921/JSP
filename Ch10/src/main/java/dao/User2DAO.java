package dao;

import java.util.List;

import db.DBHelper;
import dto.User2DTO;

public class User2DAO extends DBHelper {
	
	private static User2DAO instance = new User2DAO();
	
	public static User2DAO getInstance() {
		return instance;
	}
	
	public User2DAO() {}
	
	// 기본 CRUD
	public void insertUser2(User2DTO dto) {}
	public User2DAO selectUser2(String uid) {
		return null;
	}
	public List<User2DTO> selectUsers2() {
		return null;
	}
	public void updateUser2(User2DTO dto) {}
	public void deleteUser2(String uid) {}
}
