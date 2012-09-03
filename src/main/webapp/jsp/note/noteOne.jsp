<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Notatka</title>
</head>
<body>
	<div class="note">
		<h3>Notatka</h3>
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />


		<c:if test="${!empty oneNote}">
			<div class=form>
				<form:form method="post" action="save" commandName="oneNote">
					<table>
						<tr>
							<td><form:label path="name">
						Nazwa
					</form:label></td>
							<td><form:input class="inputSimple" path="name"
									value="${oneNote.name}" /></td>
						</tr>
						<tr>
							<td><form:label path="name">
						Zawartosc
					</form:label></td>
							<td><textarea name="contents">${oneNote.contents}</textarea></td>
						</tr>

						<tr>
							<form:input type="hidden" path="idNote" value="${oneNote.idNote}" />

							<td colspan="2"><c:if test="${!empty deleteButton}">
									<input class="buttonCancel" type=button value="Usun notatke"
										onclick="window.location.href = '<%=request.getContextPath()%>/note/one/${oneNote.idNote}/delete'">
								</c:if></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="button"
								value="zapisz" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</c:if>

		-=-
	</div>
</body>
</html>