package com.sample.webserver.models;

public class Users {

	int userID;
	String userName;
	String password;
	int userType;
	
	public Users() {
		
	}
	
	
	
	
	public int getUserType() {
		return userType;
	}




	public void setUserType(int userType) {
		this.userType = userType;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}



	public int getUserID() {
		return userID;
	}



	public String getUserName() {
		return userName;
	}



	public String getPassword() {
		return password;
	}



	public Users(UserBuilder builder) {
		this.userID = builder.userID;
		this.userName = builder.userName;
		this.password = builder.password;
		this.userType = builder.userType;
	}
	
	public static class UserBuilder {
		int userID;
		String userName;
		String password;
		int userType;
		
		
		
		public UserBuilder setUserType(int userType) {
			this.userType = userType;
			return this;
		}

		public UserBuilder setUserName(String un) {
			this.userName = un;
			return this;
		}
		
		public UserBuilder setPassword(String pw) {
			this.password = pw;
			return this;
		}
		
		public UserBuilder setUserID(int id) {
			this.userID = id;
			return this;
		}
		
		public Users build() {
			return new Users(this);
		}
		
	}
}
