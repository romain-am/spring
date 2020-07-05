<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css">

<!------------------------javascript-->
<script type="text/javascript">
	$(function() {
		$('[data-toggle="tooltip"]').tooltip();
		$(".side-nav .collapse").on(
				"hide.bs.collapse",
				function() {
					$(this).prev().find(".fa").eq(1).removeClass(
							"fa-angle-right").addClass("fa-angle-down");
				});
		$('.side-nav .collapse').on(
				"show.bs.collapse",
				function() {
					$(this).prev().find(".fa").eq(1).removeClass(
							"fa-angle-down").addClass("fa-angle-right");
				});
	})
</script>
<!------ Include the above in your HEAD tag ---------->
</head>

<div id="throbber" style="display: none; min-height: 120px;"></div>
<div id="noty-holder"></div>
<div id="wrapper">
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="https://bryanrojasq.wordpress.com">
				<img src="http://placehold.it/200x50&text=LOGO" alt="LOGO">
			</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<li><a href="#" data-placement="bottom" data-toggle="tooltip"
				href="#" data-original-title="Stats"><i
					class="fa fa-bar-chart-o"></i> </a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Admin User <b class="fa fa-angle-down"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-fw fa-user"></i> Edit
							Profile</a></li>
					<li><a href="#"><i class="fa fa-fw fa-cog"></i> Change
							Password</a></li>
					<li class="divider"></li>
					<li><i class="fa fa-fw fa-power-off"></i> <form:form
							action="${pageContext.request.contextPath}/perform_logout"
							method="POST">
							<input type="submit" value="Logout" />
						</form:form></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a href="#" data-toggle="collapse" data-target="#submenu-1"><i
						class="fa fa-fw fa-search"></i> MY INFO <i
						class="fa fa-fw fa-angle-down pull-right"></i></a>
					<ul id="submenu-1" class="collapse">
						<li><a class="manage_link_manage" href="#man"><i
								class="fa fa-angle-double-right"></i> MANAGE</a></li>
						<li><a href="#"><i class="fa fa-angle-double-right"></i>
								SUBMENU 1.2</a></li>
						<li><a href="#"><i class="fa fa-angle-double-right"></i>
								SUBMENU 1.3</a></li>
					</ul></li>
				<li><a href="#" data-toggle="collapse" data-target="#submenu-2"><i
						class="fa fa-fw fa-star"></i> MENU 2 <i
						class="fa fa-fw fa-angle-down pull-right"></i></a>
					<ul id="submenu-2" class="collapse">
						<li><a href="#"><i class="fa fa-angle-double-right"></i>
								SUBMENU 2.1</a></li>
						<li><a href="#"><i class="fa fa-angle-double-right"></i>
								SUBMENU 2.2</a></li>
						<li><a href="#"><i class="fa fa-angle-double-right"></i>
								SUBMENU 2.3</a></li>
					</ul></li>
				<li><a href="investigaciones/favoritas"><i
						class="fa fa-fw fa-user-plus"></i> MENU 3</a></li>
				<li><a href="sugerencias"><i
						class="fa fa-fw fa-paper-plane-o"></i> MENU 4</a></li>
				<li><a href="faq"><i class="fa fa-fw fa fa-question-circle"></i>
						MENU 5</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>

	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row" id="main">
				<div class="col-sm-12 col-md-12 well" id="content">
					<h1>Welcome User!</h1>
					<h2>You work for ${user.team.department.clientinfo.name} in
						${user.team.department.name} department</h2>
					<!-- Exception messages -->
					<div>${message}</div>

					<!-- ----------CONTENTS -->
					<div style="float: left; margin-right: 90px" id="manage">
					
					<!-- PROFILE PICTURE -->
					<img src="${pageContext.request.contextPath}/accountImages/${user.id}" width="100" height="120"/>
					<!-- PROFILE PICTURE -->
					
					<!-- PROFILE CV PDF -->
					<a href="${pageContext.request.contextPath}/accountPdf/${user.id}" download="${user.username}_cv.pdf">
    					<button>Download my CV</button>
					</a>
					<!-- PROFILE CV PDF -->
					
					
						<form:form action="add_cv_info" method="post"
							modelAttribute="user">
					Write "empty" and click on update to empty column
						<table border="1" cellpadding="5">
								<tr>
									<th>Name</th>
									<th>Date of birth</th>
									<th>Email</th>
									<th>Adress</th>
									<th>Telephone</th>
									<th>languages</th>
									<th>middlewares</th>
									<th>database</th>
									<th>operating_system</th>
									<th>experience</th>
									<th>frameworks</th>
									<th>Team</th>
								</tr>
								<tr>
									<td>${user.userInfo.cus_name}</td>
									<td>${user.userInfo.cus_dob}</td>
									<td>${user.userInfo.cus_email}</td>
									<td>${user.userInfo.cus_addr}</td>
									<td>${user.userInfo.cus_tel}</td>

									<form:hidden path="id" />

									<td><c:forEach items="${user.userCV.getLanguagesArray()}"
											var="language">
											<div>${language}</div>
										</c:forEach>
										<form:input type="text" path="userCV.language" /></td>
									<td><c:forEach
											items="${user.userCV.getMiddlewaresArray()}" var="middleware">
											<div>${middleware}</div>
										</c:forEach>
										<form:input type="text" path="userCV.middleware" /></td>
									<td><c:forEach items="${user.userCV.getDatabaseArray()}"
											var="database">
											<div>${database}</div>
										</c:forEach>
										<form:input type="text" path="userCV.adddatabase" /></td>
									<td><c:forEach
											items="${user.userCV.getOperating_systemArray()}"
											var="operating_system">
											<div>${operating_system}</div>
										</c:forEach>
										<form:input type="text" path="userCV.os" /></td>
									<td>${user.userCV.experience}</td>
									<td><c:forEach items="${user.userCV.getFrameworksArray()}"
											var="frameworks">
											<div>${frameworks}</div>
										</c:forEach>
										<form:input type="text" path="userCV.framework" /></td>
									<td>${user.team.head}</td>
									<td><input type="submit" value="Update" /></td>
								</tr>
							</table>
						</form:form>
					</div>



				</div>
			</div>
			<!-- /.row -->
			<!-- ADD INFOS -->
					<div style="display: block" id="manage">
						<form:form method="POST" enctype="multipart/form-data" action="accountImages/${user.id}">
									Upload my photo:
									<input type="file" name="uploadedFileName" accept="image/png, image/jpeg" />
									<input type="submit" value="Upload" />
						</form:form>
					</div>
					
					<div style="display: block" id="manage">
						<form:form method="POST" enctype="multipart/form-data" action="accountPdf/${user.id}">
							Upload my cv (in PDF):
							<input type="file" name="uploadedFileName" accept="application/pdf" />
							<input type="submit" value="Upload" />
						</form:form>
					</div>
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<script type="text/javascript">
	<!--SHOW CONTENTS-->
	var url = window.location.href;
	var manage = document.getElementById("manage");
	if (url.search('#man') > 0) {
		manage.style.display = "block";
	}
	
	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link_manage").click(function() {
				window.location.href = window.location.href + 'man';
				window.location.reload();
			});

		}, 100)

	});
	
</script>

</html>