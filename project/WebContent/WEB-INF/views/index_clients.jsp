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
						class="fa fa-fw fa-search"></i> EDIT INFORMATIONS <i
						class="fa fa-fw fa-angle-down pull-right"></i></a>
					<ul id="submenu-1" class="collapse">
						<li><a class="manage_link_addc" href="#addc"><i
								class="fa fa-angle-double-right"></i> ADD A CLIENT</a></li>
						<li><a class="manage_link_addd" href="#addd"><i
								class="fa fa-angle-double-right"></i> ADD A DEPARTMENT</a></li>
						<li><a class="manage_link_addt" href="#addt"><i
								class="fa fa-angle-double-right"></i> ADD A TEAM</a></li>
						<li><a class="manage_link_link" href="#link"><i
								class="fa fa-angle-double-right"></i> LINK A USER</a></li>
						<li><a class="manage_link_delete" href="#delete"><i
								class="fa fa-angle-double-right"></i> DELETE</a></li>
					</ul></li>
				<li><a href="#addt" data-toggle="collapse"
					data-target="#submenu-2"><i class="fa fa-fw fa-star"></i> MENU
						<i class="fa fa-fw fa-angle-down pull-right"></i></a>
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
					<h2>Clients affiliation manager</h2>
					<!-- Exception messages -->
					<div>${message}</div>

					<!-- ----------CONTENTS -->
					<!-- --LIST -->
					<div style="float: left; margin-right: 90px" id="list">
						<table border="1" cellpadding="5">
							<tr>
								<th>Name</th>
								<th>Location</th>
								<th>Telephone</th>
								<th>Departments</th>
							</tr>
							<c:forEach items="${clientInfoList}" var="clientInfo">
								<tr>
									<td>${clientInfo.name}</td>
									<td>${clientInfo.location}</td>
									<td>${clientInfo.telephone}</td>
									<td><c:forEach items="${clientInfo.departments}"
											var="department">
											<div>
												<a
													href="${pageContext.request.contextPath}/index_clients?d=${department.id}">${department.name}</a>
											</div>
										</c:forEach></td>
								</tr>
							</c:forEach>
						</table>

					</div>

					<div style="display: none; float: right" id="department">
						<table border="1" cellpadding="5">
							<tr>
								<th>Department selected</th>
								<th>Teams</th>
							</tr>
							<tr>
								<td>${department.name}</td>

								<td><table border="1" cellpadding="5">
										<tr>
											<th>Head</th>
											<th>Users</th>
										</tr>

										<c:forEach items="${teamsList}" var="team">
											<tr>
												<td>${team.head}</td>
												<td><c:forEach items="${team.users}" var="user">
														<div>${user.username}</div>
													</c:forEach></td>
											</tr>
										</c:forEach>
										<table border="0" cellpadding="5">
										<tr>
											<th>Link a team</th>
										</tr>
										<tr>
											<td><form:form action="link_team" method="post"
													modelAttribute="linkTeam">

													<form:hidden path="department.id" value="${department.id}" />

													<form:select path="id">
														<c:forEach items="${nonLinkedTeams}" var="team">
															<form:option value="${team.id}" label="${team.head}" />
														</c:forEach>
													</form:select>
													
													<input type="submit" value="Link" />

												</form:form></td>
										</tr>
										</table>
									</table></td>
						</table>
					</div>
					<!-- --LIST -->

					<!-- --ADD CLIENT -->
					<div style="display: none; margin: 0 auto; width: 40px;"
						align="center" id="add_client">
						<h2>New Client</h2>

						<form:form action="add_client" method="post"
							modelAttribute="newClient">
							<table>
								<tr>
									<td>Name:</td>
									<td><form:input path="name" /></td>
								</tr>
								<tr>
									<td>Location:</td>
									<td><form:input path="location" /></td>
								</tr>
								<tr>
									<td>Telephone:</td>
									<td><form:input path="telephone" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Save" /></td>
								</tr>
							</table>
						</form:form>

					</div>
					<!-- --ADD CLIENT -->

					<!-- --ADD DEPARTMENT -->
					<div style="margin: 0 auto; width: 40px; display: none"
						align="center" id="add_department">
						<h2>New Department</h2>

						<form:form action="add_department" method="post"
							modelAttribute="newDepartment">
							<table>
								<tr>
									<td>Name:</td>
									<td><form:input path="name" /></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><form:input path="description" /></td>
								</tr>
								<tr>
									<td>Client:</td>
									<td><form:select path="clientinfo.id">
											<c:forEach items="${clientInfoList}" var="clientInfo">
												<form:option value="${clientInfo.id}"
													label="${clientInfo.name}" />
											</c:forEach>
										</form:select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Save" /></td>
								</tr>
							</table>
						</form:form>

					</div>
					<!-- --ADD DEPARTMENT -->

					<!-- --ADD TEAM -->
					<div style="margin: 0 auto; width: 40px; display: none"
						align="center" id="add_team">
						<h2>New Team</h2>

						<form:form action="add_team" method="post"
							modelAttribute="newTeam">
							<table>
								<tr>
									<td>Head:</td>
									<td><form:input path="head" /></td>
								</tr>
								<tr>
									<td>Department:</td>
									<td><form:select path="department.id">
											<c:forEach items="${clientInfoList}" var="clientInfo">
												<c:forEach items="${clientInfo.departments}"
													var="department">
													<form:option value="${department.id}"
														label="${clientInfo.name} : ${department.name}" />
												</c:forEach>
											</c:forEach>
										</form:select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Save" /></td>
								</tr>
							</table>
						</form:form>

					</div>
					<!-- --ADD TEAM -->

					<!-- LINK A USER -->
					<div style="margin: 0 auto; width: 40px; display: none"
						align="center" id="link">
						<h3>Link user</h3>

						<form:form action="link" method="post" modelAttribute="linkUser">
							<table>
								<tr>
									<td>Username(mail):</td>
									<td><form:select path="id">
											<c:forEach items="${userList}" var="userItem">
												<form:option value="${userItem.id}"
													label="${userItem.username}" />
											</c:forEach>

										</form:select></td>
								</tr>
								<tr>
									<td>Team:</td>
									<td><form:select path="team.id">
											<c:forEach items="${clientInfoList}" var="clientInfo">
												<c:forEach items="${clientInfo.departments}"
													var="department">
													<c:forEach items="${department.teams}" var="team">
														<form:option value="${team.id}"
															label="${clientInfo.name} : ${department.name} : ${team.head}" />
													</c:forEach>
												</c:forEach>
											</c:forEach>
										</form:select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Save" /></td>
								</tr>
							</table>
						</form:form>

					</div>
					<!-- LINK A USER -->

					<!-- DELETE -->
					<div style="margin: 0 auto; width: 40px; display: none"
						align="center" id="delete">
						<h3>Delete Manager</h3>

						<form:form action="delete_team" method="post"
							modelAttribute="deleteTeam">
							<table>
								<tr>
									<td>Client Informations:</td>
									<td><form:select path="id">
											<c:forEach items="${clientInfoList}" var="clientInfo">
												<c:forEach items="${clientInfo.departments}"
													var="department">
													<c:forEach items="${department.teams}" var="team">
														<form:option value="${team.id}"
															label="${clientInfo.name} : ${department.name} : ${team.head}" />
													</c:forEach>
												</c:forEach>
											</c:forEach>
										</form:select></td>
								</tr>
								<tr>
									<td>Select what to delete</td>
									<td><form:select path="deleteItem">
											<form:option value="Team" label="Team" />
											<form:option value="Department" label="Department" />
											<form:option value="Client" label="Client" />
										</form:select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Delete" /></td>
								</tr>
							</table>
						</form:form>

					</div>
					<!-- DELETE -->
					
					<!-- SEARCH USERS BY CV -->
					<div style="display: block" id="manage">
						<form:form method="POST" action="search_cvs">
							<table>
								<tr>
									<td>Search users by skills (type Keywords like java mysql etc..):</td>
									<td><input type="text" name="keywords" /></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" value="Search" /></td>
								</tr>
							</table>
						</form:form>
					</div>
					<!-- LIST OF RETURNED USERS -->
					<div style="float: left; margin-right: 90px" id="list">
						<table border="1" cellpadding="5">
							<tr>
									<th>Name</th>
									<th>Date of birth</th>
									<th>Email</th>
									<th>Adress</th>
									<th>Telephone</th>
							</tr>
							<c:forEach items="${userInfoList}" var="user">
								<tr>
									<td>${user.cus_name}</td>
									<td>${user.cus_dob}</td>
									<td>${user.cus_email}</td>
									<td>${user.cus_addr}</td>
									<td>${user.cus_tel}</td>
								</tr>
							</c:forEach>
						</table>

					</div>
					<!-- LIST OF RETURNED USERS -->

				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<script type="text/javascript">
<!--SHOW CONTENTS-->
	var url = window.location.href;

	var department = document.getElementById("department");
	var add_client = document.getElementById("add_client");
	var add_department = document.getElementById("add_department");
	var link = document.getElementById("link");
	var deleteUrl = document.getElementById("delete");

	if (url.indexOf('index_clients?d=') > -1) {
		department.style.display = "block";
	}

	if (url.search('#addc') > 0) {
		add_client.style.display = "block";
	}

	if (url.search('#addd') > 0) {
		add_department.style.display = "block";
	}

	if (url.search('#addt') > 0) {
		add_team.style.display = "block";
	}
	if (url.search('#link') > 0) {
		link.style.display = "block";
	}
	if (url.search('#delete') > 0) {
		deleteUrl.style.display = "block";
	}

	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link_addc").click(function() {
				window.location.href = window.location.href + 'addc';
				window.location.reload();
			});

		}, 100)

	});

	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link_addd").click(function() {
				window.location.href = window.location.href + 'addd';
				window.location.reload();
			});

		}, 100)
	});

	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link_addt").click(function() {
				window.location.href = window.location.href + 'addt';
				window.location.reload();
			});

		}, 100)
	});
	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link_link").click(function() {
				window.location.href = window.location.href + 'link';
				window.location.reload();
			});

		}, 100)
	});
	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link_delete").click(function() {
				window.location.href = window.location.href + 'delete';
				window.location.reload();
			});

		}, 100)
	});
</script>

</html>