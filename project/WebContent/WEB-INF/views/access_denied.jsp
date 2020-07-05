<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<!-- Title -->
<title>Sorry, This Page Can&#39;t Be Accessed</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.411.css"
	crossorigin="anonymous" />
</head>
<body class="bg-dark text-white py-5">
	<div class="container py-5">
		<div class="row">
			<div class="col-md-2 text-center">
				<p>
					<i class="fa fa-exclamation-triangle fa-5x"></i><br />Status Code:
					403
				</p>
			</div>
			<div class="col-md-10">
				<h3>OPPSSS!!!! Sorry...</h3>
				<p>
					Sorry, your access is refused due to security reasons of our server
					and also our sensitive data.<br />Please go back to the previous
					page to continue browsing.
				</p>
				<a class="btn btn-danger" href="javascript:history.back()">Go
					Back</a>
			</div>
		</div>
	</div>

	<div id="footer" class="text-center">Hak Cipta 2018, Garuda Cyber
		Technologies</div>
</body>
</html>