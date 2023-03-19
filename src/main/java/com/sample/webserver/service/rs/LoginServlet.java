package com.sample.webserver.service.rs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sample.webserver.interfaces.DBOperationsListener;
import com.sample.webserver.interfaces.LocalSqliteListener;
import com.sample.webserver.models.Users;
import com.sample.webserver.services.LocalSqlite;

/**
 * Servlet implementation class LoginServlet
 */
@MultipartConfig
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LocalSqlite sqlite;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users users = new Users.UserBuilder().setUserName(username).setPassword(password).build();
		sqlite = new LocalSqlite(new LocalSqliteListener() {

			public void onConnectionError(Exception e) {
				System.out.println("Error connnecting to sqlite");
			};
		});
		sqlite.login(users, new DBOperationsListener() {

			@Override
			public void onSuccess(Users users) {
				HttpSession session = request.getSession();
				session.setAttribute("loginSuccess", true);
				sqlite.getAllUsers(new DBOperationsListener() {

					@Override
					public void onSuccess(List<Users> usersList) {
						try {
							if (users.getUserType() == 1) {
								session.setAttribute("admin", users);
								session.setAttribute("adminUsers", new Gson().toJson(usersList));
								response.sendRedirect("dashboard.jsp");
							} else {
								session.setAttribute("users", users);
								response.sendRedirect("helloworld.jsp");
							}
						} catch (Exception e) {
							System.out.println("ERROR : " + e.getMessage());
							out.println("Hello world");
						}
					}

					@Override
					public void onError(Exception e) {
						// TODO Auto-generated method stub
						System.out.println(e.getMessage());
					}
				});

			}

			@Override
			public void onError(Exception e) {
				System.out.println("onError : " + e.getMessage());
				HttpSession session = request.getSession();
				session.setAttribute("loginError", true);
				try {
					response.sendRedirect("helloworld.jsp");
				} catch (Exception e2) {
					System.out.println("ERROR : " + e2.getMessage());
					out.println("Hello world");
				}

			}
		});

	}

}
