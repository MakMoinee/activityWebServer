<!DOCTYPE html>
<%@page import="com.sample.webserver.common.*" %>
	<%@ page session="true" %>
		<html lang="en">

			<head>
				<link rel="icon" href="<%= Common.hostName %>/assets/images/valorantlogo.png"
				type="image/x-icon">
				<meta charset="utf-8">
				<meta name="viewport"
					content="width=device-width, initial-scale=1, shrink-to-fit=no">
				<meta name="description" content="">
				<meta name="author" content="Template Mo">
				<link
					href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900"
					rel="stylesheet">

				<title>Valorant InfoSys</title>
				<link rel="stylesheet" type="text/css"
					href="<%= Common.hostName %>/assets/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css"
					href="<%= Common.hostName %>/assets/css/font-awesome.css">
				<link rel="stylesheet" type="text/css"
					href="<%= Common.hostName %>/assets/css/templatemo-art-factories.css">
				<link rel="stylesheet" type="text/css"
					href="<%= Common.hostName %>/assets/css/owl-carousel.css">

				<link
					href="//db.onlinewebfonts.com/c/e7322a6673613ab13604fadda3d20e56?family=VALORANT"
					rel="stylesheet" type="text/css"/>
				<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
				<header class="header-area header-sticky">
					<div class="container">
						<div class="row">
							<div class="col-12">
								<nav class="main-nav">
									<!-- ***** Logo Start ***** -->
									<a href="#" class="logo" style="font-family: 'Valorant';">Valorant
										InfoSys</a>
									<!-- ***** Logo End ***** -->
									<!-- ***** Menu Start ***** -->
									<ul class="nav">
										<li class="scroll-to-section"><a href="#" class="active">Home</a></li>
										<li class="scroll-to-section"><a href="#about">About</a></li>
										<li class="scroll-to-section"><a href="<%= Common.hostName %>/weapon.jsp">Weapons</a></li>
										<li class="scroll-to-section"><a href="#login" data-toggle="modal"
												data-target="#loginModal">Login</a></li>
									</ul>
									<!-- ***** Menu End ***** -->
								</nav>
							</div>
						</div>
					</div>
				</header>
				<!-- ***** Header Area End ***** -->


				<!-- ***** Welcome Area Start ***** -->
				<div class="welcome-area" id="welcome"
					style="background-color: #e80032 !important;">

					<!-- ***** Header Text Start ***** -->
					<div class="header-text">
						<div class="container">
							<div class="row">
								<div class="left-text col-lg-6 col-md-6 col-sm-12 col-xs-12"
									data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">
									<h1>
										Valorant InfoSys is free <strong>for YOU</strong>
									</h1>
									<h2>
										By: <b>Jenneth Canlas</b>
									</h2>
									<p>Create an account and free to use nice and exciting contents from
										valorant</p>
									<a href="#about" class="main-button-slider">Find Out More</a>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12"
									data-scroll-reveal="enter right move 30px over 0.6s after 0.4s">
									<img src="<%= Common.hostName %>/assets/images/hey.png"
									class="rounded img-fluid d-block mx-auto"
									alt="First Vector Graphic">
								</div>
							</div>
						</div>
					</div>
					<!-- ***** Header Text End ***** -->
				</div>
				<!-- ***** Welcome Area End ***** -->



				<!-- ***** Contact Us End ***** -->


				<!-- ***** Footer Start ***** -->
				<footer>
					<div class="container">
						<div class="row">
							<div class="col-lg-7 col-md-12 col-sm-12">
								<p class="copyright">
									Copyright &copy; 2023 Valorant InfoSys Company
								</p>
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
				<script src="<%= Common.hostName %>/assets/js/jquery-2.1.0.min.js"></script>
				<!-- Bootstrap -->
				<script src="<%= Common.hostName %>/assets/js/popper.js"></script>
				<script src="<%= Common.hostName %>/assets/js/bootstrap.min.js"></script>
				<!-- Plugins -->
				<script src="<%= Common.hostName %>/assets/js/owl-carousel.js"></script>
				<script src="<%= Common.hostName %>/assets/js/scrollreveal.min.js"></script>
				<script src="<%= Common.hostName %>/assets/js/waypoints.min.js"></script>
				<script src="<%= Common.hostName %>/assets/js/jquery.counterup.min.js"></script>
				<script src="<%= Common.hostName %>/assets/js/imgfix.min.js"></script>

				<!-- Global Init -->
				<script src="<%= Common.hostName %>/assets/js/custom.js"></script>

				<div class="modal fade " id="loginModal" tabindex="-1" role="dialog"
					aria-labelledby="loginModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="loginModalLabel" style="font-family:
									'Valorant';">Login</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="row">
									<form action="<%= Common.hostName %>/login" method="POST"
										enctype="multipart/form-data"
										autocomplete="off">
										<center>
											<div class="form-group">
												<input required type="text" style="width:350px;margin-left: 50px;"
													name="username" id="un" placeholder="Username">
											</div>
											<div class="form-group">
												<input required type="password" style="width:350px;margin-left:
													50px;"
													name="password" id="pw" placeholder="Password">
											</div>
										</center>

									</div>
								</div>
								<div class="modal-footer">
									<button style="font-family:
										'Valorant';" type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button style="font-family:
										'Valorant'; background-color:
										#e80032" type="submit" class="btn btn-primary">Login</button>
								</div>
							</form>
						</div>
					</div>

					<input type="hidden" id="loginSuccess" name="loginSuccess"
						value="${sessionScope.loginSuccess}">
					<input type="hidden" id="loginError" name="loginSuccess"
						value="${sessionScope.loginError}">
				</div>

				<script>
			let login = document.getElementById("loginSuccess").value;
			let loginError = document.getElementById("loginError").value;

			if(login){
				setTimeout(() => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Successfully Login',
                    showConfirmButton: false,
                    timer: 800
                });

				<% request.getSession().removeAttribute("loginSuccess"); %>
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

				<% request.getSession().removeAttribute("loginError"); %>
            }, 800);
			}

		</script>
			</body>


		</html>