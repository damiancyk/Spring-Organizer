<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Zaproszenie</title>
<meta name="keywords" content="sitemesh" />
</head>
<body>
	<div class="team">
		<h3>Zaproszenie</h3>

		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br /> Pamietaj - aby wyslac
		zaproszenie, <br /> musisz posiadac te osobe w kontaktach! <br /> <br />

		<select id="selectList"
			onChange="if(this.value != 'none') document.location = this.value;">
			<option value="none">..wybierz kontakt..</option>
			<c:if test="${!empty contactList}">
				<c:forEach items="${contactList}" var="cont">
					<option value="invitation/${cont.idPosition.idUser}">${cont.idPosition.login}</option>

				</c:forEach>


			</c:if>
		</select>

		<c:if test="${!empty invitationBean}">
			<div class=form>
				<form:form method="post" action="send" commandName="invitationBean">
					<form:input type="hidden" path="idUser.idUser"
						value="${invitationBean.idUser.idUser}" />
					<form:input type="hidden" path="idTeam.idTeam"
						value="${invitationBean.idTeam.idTeam}" />
					<input type="submit" class="button" value="wyslij zaproszenie" />
				</form:form>
			</div>
		</c:if>


		<br /> <br />
	</div>
</body>
</html>