package com.sample.webserver.service.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sample.webserver.interfaces.DBOperationsListener;
import com.sample.webserver.interfaces.LocalSqliteListener;
import com.sample.webserver.models.ErrorResponse;
import com.sample.webserver.models.Users;
import com.sample.webserver.services.LocalSqlite;

/**
 * Servlet implementation class LoginAPI
 */
@MultipartConfig
@WebServlet("/api/login")
public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LocalSqlite sqlite = new LocalSqlite(new LocalSqliteListener() {

		public void onConnectionError(Exception e) {
			System.out.println("Error connnecting to sqlite");
		};
	});

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAPI() {
		super();
		// TODO Auto-generated constructor stub
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
		response.setContentType("application/json");

//		Enumeration<String> headerNames = request.getHeaderNames();
//
//		while (headerNames.hasMoreElements()) {
//
//			String headerName = headerNames.nextElement();
//			System.out.println("HEADER: " + headerName);
//			Enumeration<String> headers = request.getHeaders(headerName);
//			while (headers.hasMoreElements()) {
//				String headerValue = headers.nextElement();
//				System.out.println("headerValue: " + headerValue);
//			}
//
//		}

//		System.out.println(request.get);
//		JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
//		Users users = new Users.UserBuilder().setPassword(jsonObject.getString("password"))
//				.setUserName(jsonObject.getString("username")).build();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Users users = new Users.UserBuilder().setUserName(username).setPassword(password).build();
		PrintWriter out = response.getWriter();
		sqlite.login(users, new DBOperationsListener() {

			@Override
			public void onSuccess(Users users) {
				try {
					out.print(new Gson().toJson(users));
				} catch (Exception e) {
					System.out.println("ERROR : " + e.getMessage());
				}
			}

			@Override
			public void onError(Exception e) {
				System.out.println("onError : " + e.getMessage());
				try {
					ErrorResponse errResp = new ErrorResponse();
					errResp.setErrorMessage(e.getMessage());
					out.print(new Gson().toJson(errResp));
				} catch (Exception e2) {
					System.out.println("ERROR : " + e2.getMessage());
				}

			}
		});

	}

}
