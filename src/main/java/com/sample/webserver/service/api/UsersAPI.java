package com.sample.webserver.service.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
 * Servlet implementation class UsersAPI
 */
@MultipartConfig
@WebServlet("/api/users")
public class UsersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LocalSqlite sql;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		sql = new LocalSqlite(new LocalSqliteListener() {

			@Override
			public void onConnectionError(Exception e) {
				// TODO Auto-generated method stub
				System.out.println("onConnectionError on /api/users GET:" + e.getMessage());
			}
		});
		sql.getAllUsersOnly(new DBOperationsListener() {

			@Override
			public void onSuccess(List<Users> users) {
				// TODO Auto-generated method stub
				out.println(new Gson().toJson(users));
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				System.out.println("Error getting all users:" + e.getMessage());
			}
		});
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null) {
			doGet(request, response);
			return;
		}
		sql = new LocalSqlite(new LocalSqliteListener() {

			@Override
			public void onConnectionError(Exception e) {
				System.out.println("onConnectionError on /api/users DELETE:" + e.getMessage());

			}
		});

		if (action.equalsIgnoreCase("delete")) {
			String id = request.getParameter("id");
			if (id == null) {
				response.sendError(500, "empty id");
			} else {

				sql.deleteUser(Integer.parseInt(id), new DBOperationsListener() {

					@Override
					public void onSuccess() {
						PrintWriter out;
						try {
							out = response.getWriter();
							out.print("Successfully Deleted");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					@Override
					public void onError(Exception e) {
						System.out.println("Error deleting  user:" + e.getMessage());
					}
				});
			}
		} else if (action.equalsIgnoreCase("update")) {
			String username = request.getParameter("username");
			String userID = request.getParameter("userID");
			String password = request.getParameter("password");
			Users users = new Users.UserBuilder()
					.setUserName(username)
					.setUserID(Integer.parseInt(userID))
					.setPassword(password)
					.build();

			sql.updateUser(users, new DBOperationsListener() {

				@Override
				public void onSuccess() {
					PrintWriter out;
					try {
						out = response.getWriter();
						out.print("Successfully Updated");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				@Override
				public void onError(Exception e) {
					System.out.println("Error updating user:" + e.getMessage());

				}
			});
		}else {
			doGet(request, response);
		}
	}

}
