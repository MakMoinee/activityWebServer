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
			if (this.con == null || this.con.isClosed()) {
				Class.forName("org.sqlite.JDBC");
				this.con = DriverManager.getConnection(connectionString);
				System.out.println("Sqlite connected");
			}
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
				users.setUserType(set.getInt("userType"));
				users.setUserID(set.getInt("userID"));
				if (!set.isClosed())
					set.close();
				dbListener.onSuccess(users);

			} else {
				if (!set.isClosed())
					set.close();
				if (!this.con.isClosed())
					this.con.close();
				dbListener.onError(new Exception("empty"));

			}
		} catch (Exception e) {
			try {
				if (!this.con.isClosed())
					this.con.close();
			} catch (Exception e2) {

			}
			dbListener.onError(e);
		}
	}

	public void getAllUsers(DBOperationsListener dbListener) {
		try {
			stmt = this.con.prepareStatement(Common.getAllUsersQuery);
			ResultSet set = stmt.executeQuery();
			List<Users> usersList = new ArrayList<>();
			while (set.next()) {
				Users users = new Users.UserBuilder().setUserID(set.getInt("userID"))
						.setUserName(set.getString("userName")).setUserType(set.getInt("userType")).build();

				usersList.add(users);
			}

			if (!this.con.isClosed())
				this.con.close();

			if (!set.isClosed())
				set.close();

			if (usersList.size() > 0) {
				dbListener.onSuccess(usersList);
			} else {
				dbListener.onError(new Exception("empty"));
			}
		} catch (Exception e) {
			try {
				if (!this.con.isClosed())
					this.con.close();
			} catch (Exception e2) {

			}
			dbListener.onError(e);
		}
	}

	public void getAllUsersOnly(DBOperationsListener dbListener) {
		try {
			stmt = this.con.prepareStatement(Common.getAllUsersOnlyQuery);
			ResultSet set = stmt.executeQuery();
			List<Users> usersList = new ArrayList<>();
			while (set.next()) {
				Users users = new Users.UserBuilder().setUserID(set.getInt("userID"))
						.setUserName(set.getString("userName")).setUserType(set.getInt("userType")).build();

				usersList.add(users);
			}

			if (!this.con.isClosed())
				this.con.close();

			if (!set.isClosed())
				set.close();

			if (usersList.size() > 0) {
				dbListener.onSuccess(usersList);
			} else {
				dbListener.onError(new Exception("empty"));
			}
		} catch (Exception e) {
			try {
				if (!this.con.isClosed())
					this.con.close();
			} catch (Exception e2) {

			}
			dbListener.onError(e);
		}
	}

	public void createUser(Users users, DBOperationsListener dbListener) {
		try {

			stmt = this.con.prepareStatement(Common.insertUserQuery);
			stmt.setString(1, users.getUserName());
			stmt.setString(2, users.getPassword());
			int affectedRows = stmt.executeUpdate();
			if (affectedRows > 0) {
				if (!this.con.isClosed())
					this.con.close();
				dbListener.onSuccess();
			} else {
				if (!this.con.isClosed())
					this.con.close();
				dbListener.onError(new Exception("failed to add"));
			}

		} catch (Exception e) {
			try {
				con.close();
			} catch (Exception e2) {

			}
			dbListener.onError(e);
		}
	}

	public void deleteUser(int userID, DBOperationsListener dbOperationsListener) {
		try {

			stmt = this.con.prepareStatement(Common.deleteUserQuery);
			stmt.setInt(1, userID);
			int affectedRows = stmt.executeUpdate();
			if (!this.con.isClosed())
				this.con.close();
			if (affectedRows > 0) {
				dbOperationsListener.onSuccess();
			} else {
				dbOperationsListener.onError(new Exception("failed to delete"));
			}
		} catch (Exception e) {
			try {
				con.close();
			} catch (Exception e2) {

			}
			dbOperationsListener.onError(e);
		}
	}

	public void updateUser(Users users, DBOperationsListener dbOperationsListener) {
		try {

			stmt = this.con.prepareStatement(Common.updateUserQuery);
			stmt.setString(1, users.getUserName());
			stmt.setString(2, users.getPassword());
			stmt.setInt(3, users.getUserID());

			int affectedRows = stmt.executeUpdate();
			if (!this.con.isClosed())
				this.con.close();
			if (affectedRows > 0) {
				dbOperationsListener.onSuccess();
			} else {
				dbOperationsListener.onError(new Exception("failed to update"));
			}
		} catch (Exception e) {
			try {
				con.close();
			} catch (Exception e2) {

			}
			dbOperationsListener.onError(e);
		}
	}

}
