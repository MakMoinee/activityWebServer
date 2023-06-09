package com.sample.webserver.service.rs;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class UsersServlet
 */
@MultipartConfig
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LocalSqlite sql;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;");

		PrintWriter out = response.getWriter();
		out.println("<p>Hello World</p>");

		response.sendRedirect("/webServerActivity/helloworld.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rawUserID = request.getParameter("userID");
		HttpSession session = request.getSession();
		if (rawUserID.equals("")) {
			session.setAttribute("errorDeleteUser", true);
			response.sendRedirect("dashboard.jsp");
		} else {
			int userID = Integer.parseInt(rawUserID);
			sql = new LocalSqlite(new LocalSqliteListener() {

				@Override
				public void onConnectionError(Exception e) {
					System.out.println("onConnectionError on UsersServlet: " + e.getMessage());
				}
			});

			sql.deleteUser(userID, new DBOperationsListener() {

				@Override
				public void onSuccess() {
					session.setAttribute("successDeleteUser", true);
					loadAllUsers(response, session);
				}

				@Override
				public void onError(Exception e) {
					session.setAttribute("errorDeleteUser", true);
					doRedirect(response);
				}
			});

		}

	}

	private void loadAllUsers(HttpServletResponse response, HttpSession session) {
		sql = new LocalSqlite(new LocalSqliteListener() {

			@Override
			public void onConnectionError(Exception e) {
				System.out.println("onConnectionError on loadAllUsers: " + e.getMessage());
			}
		});

		sql.getAllUsers(new DBOperationsListener() {

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
