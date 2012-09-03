<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Rejestracja</title>
</head>
<body>
	<div class="register">
		<h3>Rejestracja</h3>
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />


		<c:if test="${!empty user}">
			<div class=form>

				<br/>
				<form:form method="post" action="register/check" commandName="user">
					<table>
						<tr>
							<td><form:label path="login">
						Login
					</form:label></td>
							<td><form:input class="inputSimple" path="login"
									value="${user.login}" /></td>
						</tr>
						<tr>
							<td><form:label path="password">
						Haslo
					</form:label></td>
							<td><form:input class="inputSimple" type="password"
									path="password" value="${user.password}" /></td>
						</tr>
						<tr>
							<td colspan="2">
										<div class="messageGood">
				<br />..pamiętaj, aby uzupelnic swoje dane osobowe po pomyślnym
				zarejstrowaniu! <br /> ..to umożliwi między innymi powiadamianie Cię o
				nadchodzących zdarzeniach..<br/>..i zadaniach, które kiedyś trzeba zrobić :)
				</div>
							<input type="submit" class="button"
								value="zakoncz rejestracje" /> <c:if
									test="${not empty param.messageRegisterFailed}">
									<div class="messageAlert">
										Nie wpisales danych <br />lub uzytkownik o podanym loginie juz
										istnieje!
									</div>
								</c:if></td>
						</tr>
					</table>
				</form:form>
			</div>
		</c:if>

		-=-
	</div>
</body>
</html>