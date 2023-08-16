package kr.farmstory1.db;

public class SQL {
	
	// terms
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	// User 
	public static final String SELECT_USER		= "SELECT * FROM `User` WHERE `uid`=? AND `pass`=?";
}
