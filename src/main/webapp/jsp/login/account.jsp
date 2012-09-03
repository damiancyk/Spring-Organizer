<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Moje konto</title>
</head>
<body>

	<div class="account">
		<h3>Moje konto</h3>

			<%
				if (request.getParameter("messageFromLogin") != null) {
					out.print("<div class='messageAlert'>");
					out.print(request.getParameter("messageFromLogin"));
					out.print("</div>");
				}
			%>
		
		<c:if test="${!empty hashEmail}">
			<img src="http://www.gravatar.com/avatar/${hashEmail}?size=80" />
		</c:if>

		<c:if test="${!empty user}">
			<div class="messageGood">
				Jesteś zalogowany jako: <b>${user.login}</b>
			</div>
		</c:if>

		<c:if test="${!empty details}">

			<div class=form>
				<form:form method="post" action="account/update"
					commandName="details">
					<table>
						<tr>
							<td><form:label path="email">
						Email
					</form:label></td>
							<td><form:input class="inputSimple" path="email"
									value="${details.email}" /> <i>..(warto podac wlasciwy)</i></td>
						</tr>
						<tr>
							<td><form:label path="firstname">
						Imie
					</form:label></td>
							<td><form:input class="inputSimple" path="firstname"
									value="${details.firstname}" /></td>
						</tr>

						<tr>
							<td><form:label path="lastname">
						Nazwisko
					</form:label></td>
							<td><form:input class="inputSimple" path="lastname"
									value="${details.lastname}" /></td>
						</tr>

						<tr>
							<td><form:label path="telephone">
						Telefon
					</form:label></td>
							<td><form:input class="inputSimple" path="telephone"
									value="${details.telephone}" /></td>
						</tr>

						<tr>
							<td><form:label path="about">
						O sobie
					</form:label></td>
							<td><textarea name="about">${details.about}</textarea></td>
						</tr>

						<tr>
							<td colspan="2"><form:input type="hidden" path="idDetails"
									value="${details.idDetails}" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="button"
								value="zapisz" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</c:if>
	</div>

</body>
</html>