<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
						class="fa fa-fw fa-search"></i> USERS <i
						class="fa fa-fw fa-angle-down pull-right"></i></a>
					<ul id="submenu-1" class="collapse">
						<li><a class="manage_link" href="#"><i
								class="fa fa-angle-double-right"></i> MANAGE CREDENTIALS</a></li>
						<li><a href="${pageContext.request.contextPath}/index_clients"><i class="fa fa-angle-double-right"></i>
								CLIENTS AFFILIATION</a></li>
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
					<h1>Welcome Admin!</h1>
					<h2>You can manage the users and their affiliation to clients</h2>
					<!-- Exception messages -->
					<div>${message}</div>

					<!-- ----------CONTENTS -->
					<div style="float: left; margin-right: 90px; display: none"
						id="manage">
						<table border="1" cellpadding="5">
							<tr>
								<th>Username(mail)</th>
								<th>Creation Date</th>
								<th>Authorities</th>
								<th>Enabled</th>
								<th>Account Non Expired</th>
								<th>Credentials Non Expired</th>
								<th>Account Non locked</th>
							</tr>
							<c:forEach items="${listUsers}" var="user">
								<tr>
									<td>${user.username}</td>
									<td>${user.dateCreated}</td>
									<td><c:forEach items="${user.userAuthority}" var="auth">
											<div>${auth.authority.name}</div>
										</c:forEach></td>
									<td>${user.enabled}</td>
									<td>${user.accountNonExpired}</td>
									<td>${user.credentialsNonExpired}</td>
									<td>${user.accountNonLocked}</td>
									<td><a
										href="${pageContext.request.contextPath}/index_admin?id=${user.id}">Edit</a>
										&nbsp;&nbsp;&nbsp; <a
										href="${pageContext.request.contextPath}/delete?id=${user.id}">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</table>

					</div>
					<div style="float: right; display: none" id="edit">
						<form:form action="save" method="post" modelAttribute="user">
							<table>
								<tr>
									<td>Username(mail) : ${user.username}</td>
									<td><form:hidden path="id" /></td>
								</tr>
								<tr>
									<td>
								</tr>
								<tr>
									<td>
								</tr>

								<tr>
									<td>Enabled:</td>
									<td><form:select path="enabled">
											<form:option value="true" label="true" />
											<form:option value="false" label="false" />
										</form:select></td>
								</tr>
								<tr>
									<td>Account non expired:</td>
									<td><form:select path="AccountNonExpired">
											<form:option value="true" label="true" />
											<form:option value="false" label="false" />
										</form:select></td>
								</tr>
								<tr>
									<td>Credentials non expired:</td>
									<td><form:select path="CredentialsNonExpired">
											<form:option value="true" label="true" />
											<form:option value="false" label="false" />
										</form:select></td>
								</tr>
								<tr>
									<td>Account non locked:</td>
									<td><form:select path="AccountNonLocked">
											<form:option value="true" label="true" />
											<form:option value="false" label="false" />
										</form:select></td>
								</tr>

								<tr>
									<td colspan="2"><input type="submit" value="Save" /></td>
								</tr>
							</table>
						</form:form>
					</div>

					<div style="float: right; display: none" id="addrolediv">
						<form action="#" method="post" id="addrole">
						<sec:csrfInput/>
							<table border="1" cellpadding="5">
								<tr>
									<th>Username(mail)</th>
									<th>Add role</th>
								</tr>
								<tr>
									<td>${user.username}</td>
									<td>
									<select id="authTypeSelected">
									<c:forEach items="${authTypes}" var="auth">
											<option value='<c:out value="${auth.name()}"/>'><c:out value="${auth.name()}"/></option>
										</c:forEach>
										</select>										
										</td>
									<td><input type="submit" value="Add" />
									<input id="userId" type="hidden" value="${user.username}">
									</td>
								</tr>
							</table>
						</form>
					</div>

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
	var manage = document.getElementById("manage");
	var edit = document.getElementById("edit");
	if (url.search('#m') > 0 || url.indexOf('index_admin?id=') > -1) {
		manage.style.display = "block";
	}
	if (url.indexOf('index_admin?id=') > -1) {
		edit.style.display = "block";
		addrolediv.style.display="block";
	}

	$(document).ready(function() {
		setTimeout(function() {
			$(".manage_link").click(function() {
				window.location.href = window.location.href + 'm';
				window.location.reload();
			});

		}, 100)
	});
	
	<!--ADD ROLE-->
	document.getElementById('addrole').addEventListener('submit', function(event){
	    event.preventDefault();
	    var form = document.getElementById('addrole');
	    form.action = window.location.pathname.split("index_admin").pop() + "addrole?id=" + document.getElementById('userId').value + "&role=" + document.getElementById('authTypeSelected').value;
		form.submit();
	})
</script>

</html>