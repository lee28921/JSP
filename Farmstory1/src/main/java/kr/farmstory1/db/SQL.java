package kr.farmstory1.db;

public class SQL {
	
	// terms
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	// User 
	public static final String INSERT_USER		= "INSERT INTO `User` SET "
														+ "`uid`=?, "
														+ "`pass`=?, "
														+ "`name`=?, "
														+ "`nick`=?,"
														+ "`email`=?, "
														+ "`hp`=?, "
														+ "`zip`=?, "
														+ "`addr1`=?, "
														+ "`addr2`=?, "
														+ "`regip`=?, "
														+ "`regDate`=NOW()";
	public static final String SELECT_USER		= "SELECT * FROM `User` WHERE `uid`=? AND `pass`=?";
}
