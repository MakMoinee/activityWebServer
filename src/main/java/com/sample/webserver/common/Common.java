package com.sample.webserver.common;

public class Common {
	public static String hostName = "http://localhost:8080/webServerActivity";
	
	public static final String loginQuery = "SELECT * FROM users WHERE username=? and password=?";
	public static final String getAllUsersQuery = "SELECT * FROM users";
}
