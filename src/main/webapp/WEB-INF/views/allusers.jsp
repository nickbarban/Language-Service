<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Users</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}
</style>

</head>


<body>
	<h2>List of Users</h2>
	<table>
		<tr>
			<td>NAME</td>
			<td>Login</td>
			<td>Password</td>
			<td>Language</td>
			<td>Email</td>
			<td>State</td>
			<td></td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.name}</td>
				<td><a href="<c:url value='/edit-${user.login}-user' />">${user.login}</a></td>
				<td>${user.password}</td>
				<td>${user.language}</td>
				<td>${user.email}</td>
				<td>${user.state}</td>
				<td><a href="<c:url value='/delete-${user.login}-user' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='/new' />">Add New User</a>
</body>
</html>