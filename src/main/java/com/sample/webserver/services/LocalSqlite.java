package com.sample.webserver.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sample.webserver.common.Common;
import com.sample.webserver.interfaces.DBOperationsListener;
import com.sample.webserver.interfaces.LocalSqliteListener;
import com.sample.webserver.models.Users;

public class LocalSqlite {

	Connection con = null;
	PreparedStatement stmt = null;
	String connectionString = "jdbc:sqlite:D:\\valorantdb.db";
	LocalSqliteListener listener;

	public LocalSqlite(LocalSqliteListener li) {
		this.listener = li;
		try {
			Class.forName("org.sqlite.JDBC");
			this.con = DriverManager.getConnection(connectionString);
			System.out.println("Sqlite connected");
		} catch (Exception e) {
			listener.onConnectionError(e);
		}
	}

	public void login(Users users, DBOperationsListener dbListener) {
		try {
			stmt = this.con.prepareStatement(Common.loginQuery);
			stmt.setString(1, users.getUserName());
			stmt.setString(2, users.getPassword());
			ResultSet set = stmt.executeQuery();
			if (set.next()) {
				if (this.con.isClosed())
					this.con.close();
				users.setUserType(set.getInt("userType"));
				users.setUserID(set.getInt("userID"));
				dbListener.onSuccess(users);
			} else {
				if (this.con.isClosed())
					this.con.close();
				dbListener.onError(new Exception("empty"));
			}
		} catch (Exception e) {
			try {
				con.close();
			} catch (Exception e2) {

			}
			// TODO: handle exception
			dbListener.onError(e);
		}
	}
	
	public void getAllUsers(DBOperationsListener dbListener){
		try {
			stmt = this.con.prepareStatement(Common.getAllUsersQuery);
			ResultSet set = stmt.executeQuery();
			List<Users> usersList = new ArrayList<>();
			while(set.next()) {
				Users users = new Users.UserBuilder()
						.setUserID(set.getInt("userID"))
						.setUserName(set.getString("userName"))
						.setUserType(set.getInt("userType"))
						.build();
				
				usersList.add(users);
			}
			
			if(usersList.size()>0) {
				if (this.con.isClosed())
					this.con.close();
				dbListener.onSuccess(usersList);
			}else {
				if (this.con.isClosed())
					this.con.close();
				dbListener.onError(new Exception("empty"));
			}
		}catch(Exception e) {
			dbListener.onError(e);
		}
	}

}
