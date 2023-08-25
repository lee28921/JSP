package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.MemberDTO;

public class MemberDAO {

	public static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MemberDAO() {} 
	
	public void insertMember(MemberDTO dto) {
		
		try {
			logger.info("MemberDAO insertDAO...");
			
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `member` VALUES (?,?,?,?,?,NOW())");
			
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setString(4, dto.getPos());
			psmt.setInt(5, dto.getDep());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("MemberDAO insertMember error :"+e.getMessage());
		}
		
	}
	public MemberDTO selectMember(String uid) {
		return null;
	}
	public List<MemberDTO> selectMembers() {
		return null;
	}
	public void updateMember(MemberDTO dto) {}
	public void deleteMember(String uid) {}
}
