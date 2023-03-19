package com.sample.webserver.service.rs;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sample.webserver.interfaces.DBOperationsListener;
import com.sample.webserver.interfaces.LocalSqliteListener;
import com.sample.webserver.models.Users;
import com.sample.webserver.services.LocalSqlite;

/**
 * Servlet implementation class SignupServlet
 */
@MultipartConfig
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LocalSqlite sqlite;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRedirect(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sqlite = new LocalSqlite(new LocalSqliteListener() {

			@Override
			public void onConnectionError(Exception e) {
				// TODO Auto-generated method stub
				System.out.println("Connection Error: " + e.getMessage());
			}
		});
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if (username == null || password == null) {
			response.sendRedirect("helloworld.jsp");
		} else {
			Users users = new Users.UserBuilder().setPassword(password).setUserName(username).build();
			String usersListRaw = (String) request.getSession().getAttribute("adminUsers");
			Type listType = new TypeToken<List<Users>>() {
			}.getType();
			List<Users> usersList = new Gson().fromJson(usersListRaw, listType);
			Boolean isExist = false;

			for (Users u : usersList) {
				if (u.getUserName().equals(username)) {
					isExist = true;
					break;
				}
			}

			if (isExist) {
				session.setAttribute("addUserErrorExist", true);
				doRedirect(response);
			} else {
				sqlite.createUser(users, new DBOperationsListener() {

					@Override
					public void onSuccess() {
						session.setAttribute("successAddUser", true);
						loadAllUsers(response, session);
					}

					@Override
					public void onError(Exception e) {
						System.out.println(e.getMessage());
						session.setAttribute("errorAddUser", true);
						doRedirect(response);
					}
				});
			}
		}
	}

	private void loadAllUsers(HttpServletResponse response, HttpSession session) {
		sqlite = new LocalSqlite(new LocalSqliteListener() {

			@Override
			public void onConnectionError(Exception e) {
				System.out.println("onConnectionError on loadAllUsers: " + e.getMessage());
			}
		});

		sqlite.getAllUsers(new DBOperationsListener() {

			@Override
			public void onSuccess(List<Users> users) {
				session.setAttribute("adminUsers", new Gson().toJson(users));
				doRedirect(response);
			}

			@Override
			public void onError(Exception e) {
				doRedirect(response);
			}
		});
	}

	private void doRedirect(HttpServletResponse response) {
		try {
			response.sendRedirect("dashboard.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
