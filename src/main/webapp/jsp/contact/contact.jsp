<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Kontakty</title>
<meta name="keywords" content="sitemesh" />
</head>
<body>
	<div class="contact">
		<h3>Kontakty</h3>
		<a class="buttonFind" href="contact/find">szukaj kontaktow</a>  <br/><br/>

		<c:if test="${!empty contactList}">

			<c:forEach items="${contactList}" var="cont">
				<div id="element"
					onclick="window.location = '<%= request.getContextPath() %>/contact/one/${cont.idPosition.idUser} '">
					<table>
						<tr>
							<td class="login">${cont.idPosition.login}</td>
							<td class="delete"><a
								href="contact/delete/${cont.idContact}">usun kontakt</a></td>
						</tr>
					</table>
				</div>
			</c:forEach>

		</c:if>
	</div>
</body>
</html>