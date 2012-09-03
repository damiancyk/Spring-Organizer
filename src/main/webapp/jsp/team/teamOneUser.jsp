<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Druzyna - uzytkownik</title>
</head>
<body>
	<h3>Druzyna - uzytkownik</h3>
	<div class="team">
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />

		<c:if test="${!empty oneUserTeam}">
			<div class="oneContact">
				<span class="title">login: </span>${oneUserTeam.idUser.login} <br />
				<span class="title">email: </span>${oneUserTeam.idUser.idDetails.email}
				<br /> ---<br /> <span class="title">imie: </span>${oneUserTeam.idUser.idDetails.firstname}
				<br /> <span class="title">nazwisko: </span>${oneUserTeam.idUser.idDetails.lastname}
				<br />
			</div>
		</c:if>
	</div>
</body>
</html>