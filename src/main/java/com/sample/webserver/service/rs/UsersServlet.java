package com.sample.webserver.service.rs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sample.webserver.interfaces.DBOperationsListener;
import com.sample.webserver.interfaces.LocalSqliteListener;
import com.sample.webserver.models.Users;
import com.sample.webserver.services.LocalSqlite;

/**
 * Servlet implementation class UsersServlet
 */
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		sql = new LocalSqlite((LocalSqliteListener) new DBOperationsListener() {

			@Override
			public void onError(Exception e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onSuccess(Users users) {
				out.println(new Gson().toJson(users));
			}
		});

		doGet(request, response);
	}

}
