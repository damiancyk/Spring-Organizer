<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Druzyny</title>
<meta name="keywords" content="sitemesh" />
</head>
<body>
	<div class="team">
		<h3>Druzyny</h3>
		<a class="buttonAdd" href="team/add">dodaj druzyne</a> <a
			class="buttonAction" href="team/invitedMe">otrzymane zaproszenia</a>
		<br /> <br />
		<%
			if (request.getParameter("quitTeamFailed") != null) {
				out.print("<div class='messageAlert'>Nie mozesz wypisac sie z tej druzyny, jestes jej adminem!</div>");
			} else if (request.getParameter("quitTeamSuccess") != null) {
				out.print("<div class='messageGood'>Pomyslnie wypisales sie z druzyny!</div>");
			}
		%>

		<c:if test="${!empty teamList}">
			<br />
			<br />

			<c:forEach items="${teamList}" var="team">
				<div id="element"
					onclick="window.location = '<%= request.getContextPath() %>/team/one/${team.idTeam.idTeam} '">
					<table>
						<tr>
							<td class="name">${team.idTeam.name}</td>
							<td class="admin"><c:choose>
									<c:when test="${team.isAdmin==true}">
										<b>(Admin)</b>
									</c:when>
									<c:otherwise>
				(user)
				</c:otherwise>
								</c:choose></td>
						</tr>
					</table>
				</div>
			</c:forEach>

		</c:if>
		<c:if test="${!empty messageSendedInvitation}">
			<c:if test="${messageSendedInvitation==false}">
nie wyslano zaproszenia
</c:if>
		</c:if>
	</div>
</body>
</html>