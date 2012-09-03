<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	int yearDisplay = Integer.parseInt(request.getAttribute("year")
			.toString());
	int monthDisplay = Integer.parseInt(request.getAttribute("month")
			.toString());
	int dayDisplay = Integer.parseInt(request.getAttribute("day")
			.toString());
%>

<html>
<head>
<title>Dzien</title>
</head>
<body>
	<div class="diary">
		<h3>Wpis</h3>
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />

		<c:if test="${!empty diary}">
			<form:form method="post" action="update" commandName="diary">
				<div class="form">
					<table>
						<tr>
							<td><form:label path="contents">
						${year}-${month}-${day} <br />
								</form:label></td>
							<td><textarea name="contents">${diary.contents}</textarea></td>
							<form:input type="hidden" path="year" value="${year}" />
							<form:input type="hidden" path="month" value="${month}" />
							<form:input type="hidden" path="day" value="${day}" />
							<form:input type="hidden" path="idDiary" value="${diary.idDiary}" />
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="button"
								value="zapisz" /></td>
						</tr>
					</table>
				</div>
			</form:form>
		</c:if>

	</div>
</body>
</html>