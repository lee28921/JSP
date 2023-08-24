package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import dto.User1DTO;

public class user1DAO {

	private final String HOST = "jdbc:mysql://43.200.179.233:3306/userdb";
	private final String USER = "userdb";
	private final String PASS = "User!1123";
	
	public void insertUser1(User1DTO dto) {
		
		try {
			Class.forName("com.mysql.jdbc.cj.Driver");
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User1` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public User1DTO selectUser1(String uid) {
		return null;
	}
	public List<User1DTO> selectUsers1() {
		return null;
	}
	public void updateUser1(User1DTO dto) {}
	public void deleteUser1(String uid) {}
	
}
