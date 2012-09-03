<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Admin - usun uzytkownika</title>
</head>
<body>
	<input class="buttonBack" type=button value="powrot"
		onCLick="history.back()">
	<br />
	<h3>Admin - usuń użytkownika</h3>

	<c:if test="${!empty userList}">
		<table border="1">
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>login:</td>
					<td><b>${user.login}</b></td>
					<td><a
								href="deleteUser/${user.idUser}">usun tego uzytkownika!</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>