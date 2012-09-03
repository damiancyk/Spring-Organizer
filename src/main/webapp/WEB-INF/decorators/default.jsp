<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title><dec:title default="Organizer" /></title>
<link rel="stylesheet"
	href="<c:url value="/resources/style/frame.css" />" media="all" />
<link rel="stylesheet"
	href="<c:url value="/resources/style/contents.css" />" media="all" />
<dec:head />
</head>
<body>
	<div id="container">
		<div id="menuUp">
			<ul>
				<li><a href="<%=request.getContextPath()%>/welcome">ORGANIZER
						2012</a></li>
				<li><a href="<%=request.getContextPath()%>/about">PRACA MGR</a></li>
				<li><a href="<%=request.getContextPath()%>/account">MOJE
						KONTO</a></li>
				<li><a href="<c:url value='/j_spring_security_logout' />">WYLOGUJ</a></li>
				<li>
					<%
						out.print("<h6 align=\"right\">   " + new Date().toString()
								+ "</h6>");
					%>
				</li>
				<li><a href="<%=request.getContextPath()%>/admin">ADMIN</a></li>

			</ul>
		</div>
		<div id="menuLeft">

			<ul>
				<li>grupowe</li>
				<li><a class="buttonCalendar"
					href="<%=request.getContextPath()%>/action/calendar">TWÓJ
						KALENDARZ</a></li>
				<li><a class="buttonEvent"
					href="<%=request.getContextPath()%>/action/list/0/nearestMonth">LISTA</a></li>
				<li><a class="buttonTask"
					href="<%=request.getContextPath()%>/action/list/3/all">OKRESOWE</a></li>
				<li><a class="buttonTeam"
					href="<%=request.getContextPath()%>/team">TWOJE DRUZYNY</a></li>
				<li><a class="buttonContact"
					href="<%=request.getContextPath()%>/contact">KONTAKTY</a></li>
			</ul>

			<ul>
				<li>lokalne</li>
				<li><a class="buttonNote"
					href="<%=request.getContextPath()%>/note">NOTATKI</a></li>
				<li><a class="buttonDiary"
					href="<%=request.getContextPath()%>/diary">DZIENNIK</a></li>
			</ul>

			<ul>
				<li>dodatkowe</li>
				<li><a class="buttonCalculator"
					href="<%=request.getContextPath()%>/calculator">KALKULATOR</a></li>
				<li><a class="buttonExchange"
					href="<%=request.getContextPath()%>/currency">KURSY WALUT</a></li>
				<li><a class="buttonWeather"
					href="<%=request.getContextPath()%>/weather">POGODA</a></li>
			</ul>

		</div>

		<div id="contentsBody">
			<dec:body />
		</div>

		<div id="footer">Designed by Damian Pięta</div>
	</div>
</body>
</html>
