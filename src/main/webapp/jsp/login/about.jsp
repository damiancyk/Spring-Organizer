<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Contact</title>
<meta name="keywords" content="sitemesh" />
</head>
<body>


	<div id="boxAbout">
		<br />

		<h1>Temat pracy</h1>
		<p>Sieciowy, wieloużytkownikowy organizer czasu z dostępem
			mobilnym.</p>
		<br /><br />
		<h2>Kierownik pracy magisterskiej</h2>
		<p>dr inż. Grzegorz Łukawski</p>

		<h2>Autor pracy magisterskiej</h2>
		<p>Damian Pięta</p>
		<br /><br />
		<h2>Administrator, który wysyła Ci powiadomienia i któremu zawsze
			możesz się pożalić</h2>
		<p>
			organizerdamiana@gmail.com <br />
			<c:if test="${!empty hashEmailAdmin}">
				<img src="http://www.gravatar.com/avatar/${hashEmailAdmin}?size=80" />
			</c:if>
		</p>

		<br />
	</div>

</body>
</html>