package service;

import java.util.List;

import dao.User2DAO;
import dto.User2DTO;

public enum User2Service {
	
		INSINACE;
	
		private User2DAO dao = User2DAO.getInstance();
	
		// 기본 CRUD
		public void insertUser2(User2DTO dto) {
			dao.insertUser2(dto);
		}
		public User2DAO selectUser2(String uid) {
			return dao.selectUser2(uid);
		}
		public List<User2DTO> selectUsers2() {
			return dao.selectUsers2();
		}
		public void updateUser2(User2DTO dto) {
			dao.updateUser2(dto);
		}
		public void deleteUser2(String uid) {
			dao.deleteUser2(uid);
		}
}
