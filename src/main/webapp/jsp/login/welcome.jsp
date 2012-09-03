<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<div id="boxAbout">
		<div class="welcome">
			<h2>ORGANIZER 2012</h2>
			<b>${username} </b> <br />

			<%
				if (request.getParameter("messageFromLogin") != null) {
					out.print("<div class='messageGood'>");
					out.print(request.getParameter("messageFromLogin"));
					out.print("</div>");
				}
			%>

			<c:if test="${!empty hashEmail}">
				<img src="http://www.gravatar.com/avatar/${hashEmail}?size=50" />
			</c:if>

			<br /> Jesteś zalogowany! : )
		</div>
	</div>
</body>
</html>