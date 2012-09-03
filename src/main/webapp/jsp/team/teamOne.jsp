<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Druzyna</title>
</head>
<body>
	<div class="team">
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />

		<c:if test="${!empty oneTeam}">
			<h3>Druzyna: ${oneTeam.idTeam.name }</h3>
			<div id="description">
				<br /> <b>-------Opis-------</b><br /> <br />
				${oneTeam.idTeam.description}

			</div>
			<input class="buttonNavigation" type=button value="Uczestnicy"
				onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/user'">
			<br />
			<input class="buttonNavigation" type=button value="Wydarzenia"
				onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/action/1'">
			<br />
			<input class="buttonNavigation" type=button value="Zadania"
				onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/action/2'">
			<br />
			<br />

			<input class="buttonCancel" type=button value="Wypisz sie"
				onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/quit'">
			<br />

			<c:choose>

				<c:when test="${oneTeam.isAdmin==true}">
					<br />
					<div class="messageGood">
						<b>(jestes Adminem)</b>
					</div>
					<input class="buttonAdd" type=button value="Dodaj wydarzenie"
						onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/addAction/1'">
					<br />
					<input class="buttonAdd" type=button value="Dodaj zadanie"
						onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/addAction/2'">
					<br />
					<input class="buttonAction" type=button value="Wyslij zaproszenie"
						onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/invitation'">
					<br />
					<input class="buttonAction" type=button value="Zmien dane druzyny"
						onCLick="window.location = '<%= request.getContextPath() %>/team/update/${oneTeam.idTeam.idTeam}'">
					<br />
					<input class="buttonCancel" type=button value="Usun druzyne"
						onCLick="window.location = '<%= request.getContextPath() %>/team/one/${oneTeam.idTeam.idTeam}/deleteTeam'">
				</c:when>

				<c:otherwise>
					<br />
					<div class="messageAlert">
						<b>(nie jestes adminem, brak dodatkowych praw)</b>
					</div>
					<input class="buttonAdd" type=button disabled="disabled"
						value="Dodaj wydarzenie" onCLick="#">
					<br />
					<input class="buttonAdd" type=button disabled="disabled"
						value="Dodaj zadanie" onCLick="#">
					<br />
					<input class="buttonAction" type=button disabled="disabled"
						value="Wyslij zaproszenie" onCLick="#">
					<br />
					<input class="buttonAction" type=button disabled="disabled"
						value="Zmien dane druzyny" onCLick="#">
					<br />
					<input class="buttonCancel" type=button disabled="disabled"
						value="Usun druzyne" onCLick="#">
				</c:otherwise>

			</c:choose>

		</c:if>

	</div>
</body>
</html>