package service;

import java.util.List;

import dao.user1DAO;
import dto.User1DTO;

public class User1Service {

	private user1DAO dao = new user1DAO();
	
	public void insertUser1(User1DTO dto) {
		dao.insertUser1(dto);
	}
	public User1DTO selectUser1(String uid) {
		return dao.selectUser1(uid);
	}
	public List<User1DTO> selectUsers1() {
		List<User1DTO> users = dao.selectUsers1();
		return users;
	}
	public void updateUser1(User1DTO dto) {
		dao.updateUser1(dto);
	}
	public void deleteUser1(String uid) {
		dao.deleteUser1(uid);
	}
}
