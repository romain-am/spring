<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer</title>
</head>
<body>

<div align="center">
	<h2>
		New Customer
	</h2>
	
	<form:form action="save" method="post" modelAttribute="user">
		<table>
			<tr>
				<td>ID:</td>
				<td>
				${user.id}
				<form:hidden path="id" />
				</td>
			</tr>
			
			<tr>
				<td>Enabled:</td>
				<td><form:input path="enabled" /></td>
			</tr>
			<tr>
				<td>Account non expired:</td>
				<td><form:input path="AccountNonExpired" /></td>
			</tr>
			<tr>
				<td>Credentials non expired:</td>
				<td><form:input path="CredentialsNonExpired" /></td>
			</tr>
			<tr>
				<td>Account non locked:</td>
				<td><form:input path="AccountNonLocked" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>
	
</div>

</body>
</html>