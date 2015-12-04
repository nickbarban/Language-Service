<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>

	<h2>Registration Form</h2>

	<form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="name">Name: </label></td>
				<td><form:input path="name" id="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="login">Login: </label></td>
				<td><form:input path="login" id="login" /></td>
				<td><form:errors path="login" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="password">Password: </label></td>
				<td><form:input path="password" id="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="language">Select language: </label></td>
				<td><form:select path="language" >
					<form:options items="${languages}" itemLabel="name" itemValue="id"/>
					</form:select></td>
			</tr>

			<tr>
				<td colspan="3"><c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br /> Go back to
	<a href="<c:url value='/list' />">List of All Users</a>
	
</body>
</html>