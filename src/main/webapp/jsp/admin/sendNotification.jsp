<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Admin - wyslij przypomnienie</title>
</head>
<body>

	<input class="buttonBack" type=button value="powrot"
		onCLick="history.back()">
	<br />
	<h3>Admin - wyślij przypomnienie</h3>

	<input class="" type=button value="najblizsza godzina"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification/confirm/nearestHour'">
	<br />
	<br />
	<input class="" type=button value="najblizsze 12 godzin"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification/confirm/nearestTwelveHours'">
	<br />
	<br />
	<input class="" type=button value="najblizszy dzien"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification/confirm/nearestDay'">
	<br />
	<br />
	<input class="" type=button value="najblizszy tydzien"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification/confirm/nearestWeek'">
	<br />
	<br />
	<input class="" type=button value="najblizszy miesiac"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification/confirm/nearestMonth'">
	<br />
	<br />
	<input class="" type=button value="najblizszy rok"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification/confirm/nearestYear'">

</body>
</html>