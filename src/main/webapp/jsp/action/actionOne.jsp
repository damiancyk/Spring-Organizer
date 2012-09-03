<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>podglad / edycja</title>
</head>
<body>
	<div class="action">
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()">
		<h3>podglad / edycja</h3>
		<c:set var="adminAccess" value="false" scope="page" />
		<c:if test="${!empty isAdmin}">
 
			<c:choose>
				<c:when test="${isAdmin==true}">
					<c:set var="adminAccess" value="true" scope="page" />
					<div class="messageGood">
						<b>(Admin)</b> - mozesz usunac / edytowac ten wpis
					</div>
				</c:when>
				<c:otherwise>
					<div class="messageAlert">
						<b>(User)</b> - mozesz tylko przegladac wpis, nie jestes jego
						adminem
					</div>
					<c:set var="adminAccess" value="false" scope="page" />
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:if test="${!empty action}">
			<div class=form>
				<form:form method="post" action="save" commandName="action">
					<table>
						<tr>
							<td><form:label path="name">
						Nazwa
					</form:label></td>
							<td><form:input readonly="${!adminAccess }"
									class="inputSimple" path="name" value="${action.name}"
									disabled="false" /></td>
						</tr>
						<tr>
							<td><form:label path="description">
						Opis
					</form:label></td>
							<td><textarea class="big" name="description">${action.description}</textarea></td>
						</tr>

						<tr>
							<td><form:label path="priority">
						Priorytet
					</form:label></td>
							<td><select name="priority">
									<option value="${action.priority}">${action.priority}%</option>
									<option value="0">0%</option>
									<option value="10">10%</option>
									<option value="20">20%</option>
									<option value="30">30%</option>
									<option value="40">40%</option>
									<option value="50">50%</option>
									<option value="60">60%</option>
									<option value="70">70%</option>
									<option value="80">80%</option>
									<option value="90">90%</option>
									<option value="100">100%</option>

							</select></td>
						</tr>

						<tr>
							<td><form:label path="periodic">
						Okresowy
					</form:label></td>
							<td><select name="periodic">
									<option value="${action.periodic}">${action.periodic}</option>
									<option value="no">---</option>
									<option value="no">jednorazowe</option>
									<option value="day">codziennie</option>
									<option value="dayTwo">co dwa dni</option>
									<option value="week">co tydzien</option>
									<option value="month">co miesiac</option>
									<option value="year">co rok</option>
							</select></td>
						</tr>


						<tr>
							<td><form:label path="dateWhen">
						Data
					</form:label></td>
							<td><form:input readonly="${!adminAccess }"
									class="inputSimple" path="dateWhen"
									value="${action.dateWhen.toLocaleString()}" /></td>
						</tr>

						<tr>
							<form:input type="hidden" path="idAction"
								value="${action.idAction}" />

							<c:if test="${action.typeAction==1}">
								<b>Typ: wydarzenie</b>
							</c:if>
							<c:if test="${action.typeAction==2}">
								<b>Typ: zadanie</b>
							</c:if>

							<form:input type="hidden" path="typeAction"
								value="${action.typeAction}" />
							<form:input type="hidden" path="idTeam.idTeam"
								value="${action.idTeam.idTeam}" />


							<c:if test="${!empty adminAccess }">
								<c:if test="${adminAccess == true}">
									<c:if test="${empty noDelete}">
										<td colspan="2">
											<button
												onclick="window.location.href = '<%=request.getContextPath()%>/action/one/${action.idAction}/delete'">usun
												wpis</button> <br />
										</td>
									</c:if>
								</c:if>
							</c:if>
						</tr>
						<tr>
							<c:if test="${!empty adminAccess }">
								<c:if test="${adminAccess == true}">
									<td colspan="2"><input readonly="${!adminAccess }"
										type="submit" class="button" name="save" value="zapisz zmiany" /></td>
								</c:if>
							</c:if>
						</tr>
					</table>
				</form:form>
			</div>
		</c:if>

		-=-
	</div>
</body>
</html>