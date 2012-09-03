<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Strona logowania</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	
		<h3>Witaj! Wpisz swój Login i Hasło.</h3>

		<c:if test="${not empty error}">
			<div class="errorblock">
				Podałeś złe dane, spróbuj ponownie.<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
<div class="form">
		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>

			<table>
				<tr>
					<td>Login:</td>
					<td><input type='text' name='j_username' value=''></td>
				</tr>
				<tr>
					<td>Hasło:</td>
					<td><input type='password' name='j_password' /></td>
				</tr>
				<tr>
					<td colspan='2'><br/><input name="submit" class="buttonAction"
						type="submit" value="ZALOGUJ" /></td>
	
					<td colspan='2'><input name="reset" class="buttonAction"
						type="reset" value="reset" /></td>
				</tr>
				<tr>
					<td colspan='2'><br /><br/>
					<a class="buttonAdd" href="register">rejestracja</a> <c:if
							test="${not empty param.messageRegisterSuccess}">
							<div class="messageGood">
								Pomyslnie sie zarejstrowales!<br /> Aby w pelni korzystac z
								uslug Organizera, <br /> uzupelnij swoje dane osobiste w
								zakladce MOJE KONTO
							</div>
						</c:if></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>