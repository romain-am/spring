<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
</head>
<body>
	
	<h1>Search Result</h1>
	<form method="get" action="search">
	<input type="text" name="keyword"/>
	<input type="submit" value="Search"/>
	</form>
	
	<h3><a href="new">New Customer</a></h3>
	
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
		</tr>
		
		<c:forEach items="${result}" var="customer">
		<tr>
			<td>${customer.id}</td>
			<td>${customer.name}</td>
			<td>${customer.email}</td>
			<td>${customer.address}</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>