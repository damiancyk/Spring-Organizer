<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Druzyna (dodawanie-edycja)</title>
</head>
<body>
	<div class="team">
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />
		<h3>Druzyna (dodawanie-edycja)</h3>

		<form:form method="post" action="save" commandName="teamBean">
			<div class="form">
				<table>
					<tr>
						<td><form:label path="name">
						Nazwa
					</form:label></td>
						<td><form:input class="inputSimple" path="name"
								value="${teamBean.name}" /></td>
					</tr>
					<tr>
						<td><form:label path="name">
						Opis
					</form:label></td>
						<td><textarea name="description">${teamBean.description}</textarea></td>
					</tr>

					<tr>
					<form:input class="inputSimple" type="hidden" path="idTeam"
								value="${teamBean.idTeam}" />
						<td colspan="2"><input type="submit" class="button"
							value="zapisz" /></td>
					</tr>
				</table>
			</div>
		</form:form>
	</div>
</body>
</html>
