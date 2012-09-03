<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Admin - witaj</title>
</head>
<body>
	<input class="buttonBack" type=button value="powrot"
		onCLick="history.back()">
	<br />
	<h3>Admin - witaj</h3>
	<div class="messageGood">
		<b>(Admin)</b> - witaj, jesteś administratorem tej aplikacji.
	</div>
	<br /> Posiadasz dodatkowe możliwości:
	<br />
	<input class="buttonAction" type=button value="Wyslij przypomienia"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/sendNotification'">
	<br /><br/>
	<input class="buttonCancel" type=button value="Usun uzytkownika"
		onCLick="window.location = '<%=request.getContextPath()%>/admin/deleteUser'">
		
		<div class='messageGood'>
				<%
			if (request.getParameter("messageFromFunction") != null){
				out.print(request.getParameter("messageFromFunction"));
			}
		%>
</div>

</body>
</html>