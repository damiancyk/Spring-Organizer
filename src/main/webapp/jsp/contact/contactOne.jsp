<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Kontakt</title>
</head>
<body>
	<h3>Kontakt</h3>
	<div class="contact">
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />

		<c:if test="${!empty oneContact}">
			<div class="oneContact">
				<span class="title">login: </span>${oneContact.idPosition.login} <br />
				<span class="title">email: </span>${oneContact.idPosition.idDetails.email}
				<br /> ---<br /> <span class="title">imie: </span>${oneContact.idPosition.idDetails.firstname}
				<br /> <span class="title">nazwisko: </span>${oneContact.idPosition.idDetails.lastname}
				<br /><br />
				<c:if test="${!empty hashEmail}">
					<img src="http://www.gravatar.com/avatar/${hashEmail}?size=80" />
				</c:if>
			</div>
		</c:if>
	</div>
</body>
</html>