
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sample.webserver.models.Users"%>
<%@page import="com.sample.webserver.common.*"%>
<%@ page session="true"%>
<%
Users users = (Users) request.getSession().getAttribute("admin");
List<Users> usersList = new ArrayList<>();
if (users == null) {
	response.sendRedirect("helloworld.jsp");
} else {
	String usersListRaw = (String) request.getSession().getAttribute("adminUsers");
	Type listType = new TypeToken<List<Users>>() {
	}.getType();
	usersList = new Gson().fromJson(usersListRaw, listType);
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon"
	href="<%=Common.hostName%>/assets/images/valorantlogo.png"
	type="image/x-icon">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,
                    shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Template Mo">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900"
	rel="stylesheet">

<title>Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="<%=Common.hostName%>/assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=Common.hostName%>/assets/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="<%=Common.hostName%>/assets/css/templatemo-art-factory.css">
<link rel="stylesheet" type="text/css"
	href="<%=Common.hostName%>/assets/css/owl-carousel.css">
<link
	href="//db.onlinewebfonts.com/c/e7322a6673613ab13604fadda3d20e56?family=VALORANT"
	rel="stylesheet" type="text/css" />
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
.background-header {
	background-color: #e80032;
	color: #FFFFFF !important;
}
</style>
</head>

<body>

	<!-- ***** Preloader Start ***** -->
	<div id="preloader">
		<div class="jumper">
			<div></div>
			<div></div>
			<div></div>
		</div>
	</div>
	<!-- ***** Preloader End ***** -->


	<!-- ***** Header Area Start ***** -->
	<header class="header-area header-sticky background-header">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav class="main-nav">
						<!-- ***** Logo Start ***** -->
						<a href="#" class="logo"
							style="font-family: 'Valorant'; color: #FFFFFF !important;">Valorant
							InfoSys</a>
						<!-- ***** Logo End ***** -->
						<!-- ***** Menu Start ***** -->
						<ul class="nav">
							<li class="scroll-to-section"><a href="#" class="active"
								style="color: #FFFFFF !important;">Dashboard</a></li>
							<li class="scroll-to-section"><a href="<%=Common.hostName%>/aboutdash.jsp">About</a></li>
							<li class="scroll-to-section"><a href="#login"
								data-toggle="modal" data-target="#logOutModal">Logout</a></li>
						</ul>
						<!-- ***** Menu End ***** -->
					</nav>
				</div>
			</div>
		</div>
	</header>


	<div class="body flex-grow-1 px-3" style="margin-top: 150px;">
		<div class="container-lg">
			<div class="row">
				<div class="col-md-12">
					<div class="card mb-12">
						<div class="card-header">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#signUpModal"
								style="background-color: #e80032; font-family: 'Valorant';">Add
								User</button>
						</div>
						<!-- <div class="card-header">
                                        <form action="/adminusers" method="get">
                                            <div class="input-group mb-3">
                                                <input type="text"
                                                    class="form-control"
                                                    placeholder="Search User"
                                                    aria-label="Recipient's
                                                    username"
                                                    aria-describedby="basic-addon2"
                                                    name="search">
                                                <button type="submit"
                                                    class="input-group-text"
                                                    id="basic-addon2">Search</button>
                                            </div>
                                        </form>
                                    </div> -->
						<div class="card-body">
							<br>
							<div class="table-responsive">
								<table class="table border mb-0">
									<thead
										class="table-light
                                                    fw-semibold">
										<tr class="align-middle">
											<th>User ID</th>
											<th>Username</th>
											<th>User Type</th>
											<th style="text-align: right;">Action</th>
										</tr>
									</thead>
									<tbody>
										<%
										if (usersList != null) {
										%>
										<%
										for (int i = 0; i < usersList.size(); i++) {
										%>
										<tr>
											<td><%=usersList.get(i).getUserID()%></td>
											<td><%=usersList.get(i).getUserName()%></td>
											<td>
												<%
												if (usersList.get(i).getUserType() == 1) {
												%> Admin <%
												}
												%> <%
 if (usersList.get(i).getUserType() == 2) {
 %> User <%
 }
 %>
											</td>
											<td>
												<%
												if (usersList.get(i).getUserType() == 1) {
												%>
												<button
													class="btn
			                                                                btn-success disabled"
													disabled style="color: white; float: right;">View/Edit</button>
												<button
													class="btn
			                                                                btn-danger disabled"
													disabled style="color: white; float: right;">Delete</button>
												<%
												}
												%> <%
 if (usersList.get(i).getUserType() == 2) {
 %>
												<button
													class="btn
			                                                                btn-success"
													style="color: white; float: right;" data-toggle="modal"
													data-target="#viewModal<%=usersList.get(i).getUserID()%>">View/Edit</button>
												<div class="modal fade"
													id="viewModal<%=usersList.get(i).getUserID()%>"
													tabindex="-1" role="dialog"
													aria-labelledby="viewModalLabel<%=usersList.get(i).getUserID()%>"
													aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content" style="width: 450px;">
															<div class="modal-header">
																<h5 class="modal-title"
																	id="viewModalLabel<%=usersList.get(i).getUserID()%>"
																	style="font-family: 'Valorant';">View/Edit User</h5>
																<button type="button"
																	style="border: none; background: white;" class="close"
																	data-dismiss="modal" aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<div class="row">
																	<form action="/signup" method="POST"
																		enctype="multipart/form-data" autocomplete="off">
																		<div class="form-group"
																			style="margin-left: 60px; margin-bottom: 20px;">
																			<label for="Username" class="for">Username<span
																				style="color: red">*</span></label>
																		</div>
																		<div class="form-group"
																			style="margin-left: 60px; margin-top: -20px; margin-bottom: 20px;">
																			<input required type="text" name="username" id=""
																				style="width: 350px;"
																				value="<%=usersList.get(i).getUserName()%>">
																		</div>
																		<div class="form-group"
																			style="margin-left: 60px; margin-top: -12px; margin-bottom: 20px;">
																			<label for="password" class="for">Password<span
																				style="color: red">*</span></label>
																		</div>
																		<div class="form-group"
																			style="margin-left: 60px; margin-top: -20px; margin-bottom: 20px;">
																			<input required type="password"
																				pattern="[a-zA-Z0-9]+" name="password"
																				id="apassword" style="width: 350px;">
																		</div>
																		<div class="form-group"
																			style="margin-left: 60px; margin-top: -12px; margin-bottom: 20px;">
																			<label for="password" class="for">Retype
																				Password<span style="color: red">*</span>
																			</label>
																		</div>
																		<div class="form-group"
																			style="margin-left: 60px; margin-top: -20px; margin-bottom: 20px;">
																			<input required type="password"
																				pattern="[a-zA-Z0-9]+" name="repassword"
																				id="arepassword" style="width: 350px;">
																		</div>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
																<button type="submit" class="btn btn-primary"
																	name="btnSignup" style="background-color: #e80032"
																	value="admin">Add User</button>
															</div>
															</form>
														</div>
													</div>
												</div>
												<button
													class="btn
			                                                                btn-danger"
													style="color: white; float: right;" data-toggle="modal"
													data-target="#deleteUserModal<%=usersList.get(i).getUserID()%>">Delete</button>

												<div class="modal fade"
													id="deleteUserModal<%=usersList.get(i).getUserID()%>"
													tabindex="-1" role="dialog"
													aria-labelledby="deleteUserModalLabel<%=usersList.get(i).getUserID()%>"
													aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<form action="<%=Common.hostName%>/users" method="POST"
																enctype="multipart/form-data">
																<div class="modal-body">
																	<h5 class="modal-title"
																		id="deleteUserModalLabel<%=usersList.get(i).getUserID()%>">
																		Do you want to proceed deleting this user ?</h5>
																</div>
																<div class="form-group">
																	<input type="hidden" name="userID"
																		value="<%=usersList.get(i).getUserID()%>">
																</div>
																<div class="modal-footer">
																	<button type="submit" class="btn btn-primary"
																		name="btnArchive" value="yes">Yes, Proceed</button>
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">Close</button>
																</div>
															</form>
														</div>
													</div>
												</div> <%
 }
 %>

											</td>
										</tr>
										<%
										}
										%>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<div class="card-footer">
							<nav aria-label="...">
								<ul class="pagination">

								</ul>
							</nav>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


	<!-- ***** Footer Start ***** -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-7 col-md-12 col-sm-12">
					<p class="copyright">Copyright &copy; 2023 Valorant InfoSys
						Company</p>
				</div>
				<div class="col-lg-5 col-md-12 col-sm-12">
					<ul class="social">
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
						<li><a href="#"><i class="fa fa-rss"></i></a></li>
						<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script src="<%=Common.hostName%>/assets/js/jquery-2.1.0.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=Common.hostName%>/assets/js/popper.js"></script>
	<script src="<%=Common.hostName%>/assets/js/bootstrap.min.js"></script>
	<!-- Plugins -->
	<script src="<%=Common.hostName%>/assets/js/owl-carousel.js"></script>
	<script src="<%=Common.hostName%>/assets/js/scrollreveal.min.js"></script>
	<script src="<%=Common.hostName%>/assets/js/waypoints.min.js"></script>
	<script src="<%=Common.hostName%>/assets/js/jquery.counterup.min.js"></script>
	<script src="<%=Common.hostName%>/assets/js/imgfix.min.js"></script>
	<!-- Global Init -->
	<script src="<%=Common.hostName%>/assets/js/custom.js"></script>
	<div class="modal fade " id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginModalLabel"
						style="font-family: 'Valorant';">Login</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<form action="<%=Common.hostName%>/login" method="POST"
							enctype="multipart/form-data" autocomplete="off">
							<center>
								<div class="form-group">
									<input required type="text"
										style="width: 350px; margin-left: 50px;" name="username"
										id="un" placeholder="Username">
								</div>
								<div class="form-group">
									<input required type="password"
										style="width: 350px; margin-left: 50px;" name="password"
										id="pw" placeholder="Password">
								</div>
							</center>
					</div>
				</div>
				<div class="modal-footer">
					<button style="font-family: 'Valorant';" type="button"
						class="btn
                                        btn-secondary"
						data-dismiss="modal">Close</button>
					<button style="font-family: 'Valorant'; background-color: #e80032"
						type="submit"
						class="btn
                                        btn-primary">Login</button>
				</div>
				</form>
			</div>
		</div>

		<input type="hidden" id="loginSuccess" name="loginSuccess"
			value="${sessionScope.loginSuccess}"> <input type="hidden"
			id="loginError" name="loginError" value="${sessionScope.loginError}">
		<input type="hidden" id="addUserErrorExist" name="addUserErrorExist"
			value="${sessionScope.addUserErrorExist}"> <input
			type="hidden" id="successAddUser" name="successAddUser"
			value="${sessionScope.successAddUser}"> <input type="hidden"
			id="errorAddUser" name="errorAddUser"
			value="${sessionScope.errorAddUser}"> <input type="hidden"
			id="errorDeleteUser" name="errorDeleteUser"
			value="${sessionScope.errorDeleteUser}"> <input type="hidden"
			id="successDeleteUser" name="successDeleteUser"
			value="${sessionScope.successDeleteUser}">
	</div>

	<div class="modal fade" id="logOutModal" tabindex="-1" role="dialog"
		aria-labelledby="logOutModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="<%=Common.hostName%>/logout" method="GET">
					<div class="modal-body">
						<h5 class="modal-title" id="logOutModalLabel">Do you want to
							proceed logging out ?</h5>
					</div>
					<div class="modal-footer">
						<button type="submit"
							class="btn
                                        btn-primary">Yes,
							Proceed</button>
						<button type="button"
							class="btn
                                        btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog"
		aria-labelledby="signUpModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: 450px;">
				<div class="modal-header">
					<h5 class="modal-title" id="signUpModalLabel"
						style="font-family: 'Valorant';">Add User</h5>
					<button type="button" style="border: none; background: white;"
						class="close" data-coreui-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<form action="<%=Common.hostName%>/signup" method="POST"
							enctype="multipart/form-data" autocomplete="off">
							<div class="form-group"
								style="margin-left: 60px; margin-bottom: 20px;">
								<label for="Username" class="for">Username<span
									style="color: red">*</span></label>
							</div>
							<div class="form-group"
								style="margin-left: 60px; margin-top: -20px; margin-bottom: 20px;">
								<input required type="text" name="username" id=""
									style="width: 350px;">
							</div>
							<div class="form-group"
								style="margin-left: 60px; margin-top: -12px; margin-bottom: 20px;">
								<label for="password" class="for">Password<span
									style="color: red">*</span></label>
							</div>
							<div class="form-group"
								style="margin-left: 60px; margin-top: -20px; margin-bottom: 20px;">
								<input required type="password" pattern="[a-zA-Z0-9]+"
									name="password" id="apassword" style="width: 350px;">
							</div>
							<div class="form-group"
								style="margin-left: 60px; margin-top: -12px; margin-bottom: 20px;">
								<label for="password" class="for">Retype Password<span
									style="color: red">*</span></label>
							</div>
							<div class="form-group"
								style="margin-left: 60px; margin-top: -20px; margin-bottom: 20px;">
								<input required type="password" pattern="[a-zA-Z0-9]+"
									name="repassword" id="arepassword" style="width: 350px;">
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" name="btnSignup"
						style="background-color: #e80032" value="admin">Add User</button>
				</div>
				</form>
			</div>
		</div>
	</div>

	<script>
                    let login = document.getElementById("loginSuccess").value;
                    let loginError = document.getElementById("loginError").value;
                    let addUserErrorExist = document.getElementById("addUserErrorExist").value;
                    let successAddUser = document.getElementById("successAddUser").value;
                    let errorAddUser = document.getElementById("errorAddUser").value;
                    let successDeleteUser = document.getElementById("successDeleteUser").value;
                    let errorDeleteUser = document.getElementById("errorDeleteUser").value;


                    if(successDeleteUser){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully Deleted User',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("successDeleteUser");%>
                    }, 800);
                    }

                    if(errorDeleteUser){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'warning',
                        title: 'Failed to delete user, please try again later.',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("errorDeleteUser");%>
                    }, 800);
                    }


                    if(errorAddUser){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'warning',
                        title: 'Failed to add user, please try again later.',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("errorAddUser");%>
                    }, 800);
                    }

                    if(successAddUser){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully Added User',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("successAddUser");%>
                    }, 800);
                    }

                    if(addUserErrorExist){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'warning',
                        title: 'User exist already, Please create new user with unique username and password',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("addUserErrorExist");%>
                    }, 800);
                    }


                    if(login){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully Login',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("loginSuccess");%>
                    }, 800);
                    }

                    if(loginError){
                    setTimeout(() => {
                    Swal.fire({
                        position: 'center',
                        icon: 'warning',
                        title: 'Wrong username or password',
                        showConfirmButton: false,
                        timer: 800
                    });

                    <%request.getSession().removeAttribute("loginError");%>
                    }, 800);
                    }

                </script>
</body>


</html>
