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
		<h3>Zaproszenia</h3>
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />

		<c:if test="${!empty invitationList}">
			<c:forEach items="${invitationList}" var="invitation">

				<div id="element">
					<table>
						<tr>
							<td class="name">nazwa: ${invitation.idTeam.name} <br />
								opis: ${invitation.idTeam.description}
							</td>
							<td><a
								href="invitedMe/confirm/${invitation.idInvitation}/${invitation.idTeam.idTeam}">akceptuj</a></td>
							<td><a href="invitedMe/reject/${invitation.idInvitation}">odrzuc</a></td>
						</tr>

					</table>
				</div>
			</c:forEach>
		</c:if>
		---

	</div>
</body>
</html>
