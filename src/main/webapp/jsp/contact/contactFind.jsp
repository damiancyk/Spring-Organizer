<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Kontakty - szukaj</title>
<meta name="keywords" content="sitemesh" />
</head>
<body>
	<div class="contact">
		<h3>Kontakty - szukaj</h3>
		<input class="buttonBack" type=button value="powrot" onCLick="history.back()">

		<form:form method="post" action="find" commandName="userBean">
			<div class="form">
				<table>
					<tr>
						<td><form:label path="login">
						Login
					</form:label></td>
						<td><form:input class="inputSimple" path="login" value="" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="button"
							value="szukaj" /></td>
					</tr>
				</table>
			</div>
		</form:form>

		<c:if test="${!empty messageNotFound}">
	Nie znaleziono kontaktow o podanym kryterium
	</c:if>

		<c:if test="${!empty userFound}">
	Znaleziono: 

				<c:forEach items="${userFound}" var="user">
				<div id="element"
					onclick="window.location = '<%= request.getContextPath() %>/contact/one/${user.idUser} '">
					<table>
						<tr>
							<td class="login"> ${user.login}</td>
							<td class="add"><a href="add/${user.idUser}">dodaj</a></td>
						</tr>
					</table>
				</div>
				</c:forEach>
		</c:if>
	</div>
</body>
</html>