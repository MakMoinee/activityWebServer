package com.sample.webserver.common;

public class Common {
	public static String hostName = "http://localhost:8080/webServerActivity";

	public static final String loginQuery = "SELECT * FROM users WHERE username=? and password=?";
	public static final String getAllUsersQuery = "SELECT * FROM users";
	public static final String getAllUsersOnlyQuery = "SELECT * FROM users WHERE userType=2";
	public static final String insertUserQuery = "INSERT INTO users (userName,password,userType) values (?,?,2)";
	public static final String deleteUserQuery = "DELETE FROM users WHERE userID=?";
	public static final String updateUserQuery = "UPDATE users Set userName=?, password=? WHERE userID=?";

}
