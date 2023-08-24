package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.User1DTO;

public class user1DAO {

	private final String HOST = "jdbc:mysql://43.200.179.233:3306/userdb";
	private final String USER = "userdb";
	private final String PASS = "User!1123";
	
	public void insertUser1(User1DTO dto) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user1` VALUES (?,?,?,?)");
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
		
		User1DTO dto = new User1DTO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user1` WHERE `uid`=?");
			psmt.setString(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				
			}
			rs.close();
			psmt.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public List<User1DTO> selectUsers1() {
		
		List<User1DTO> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `user1`");
			
			while(rs.next()) {
				User1DTO dto = new User1DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				users.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public void updateUser1(User1DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `user1` SET `name`=?, `hp`=?, `age`=? WHERE `uid`=?");
			
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getUid());
			
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteUser1(String uid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user1` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
