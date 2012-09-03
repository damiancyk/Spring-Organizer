<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*"%>
<html>
<head>
<title>Notatki</title>
</head>
<body>
	<div class="note">
		<h3>Notatki</h3>
		<a class="buttonAdd" href="note/new">nowa</a> <br /> <br />

		<c:if test="${!empty noteList}">
			<c:forEach items="${noteList}" var="cont">
				<div id="element"
					onclick="window.location = '<%= request.getContextPath() %>/note/one/${cont.idNote}'">
					<table>
						<tr>
							<td>${cont.name}</td>
							<td>data modyfikacji:
								${cont.year}-${cont.month}-${cont.day},
								godz:${cont.hour}:${cont.minute}</td>

						</tr>
						<tr>
							<th colspan=2>${cont.contents}</th>
						</tr>
					</table>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>