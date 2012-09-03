<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Druzyna (wydarzenia-zadania)</title>
</head>
<body>
	<div class="action">
		<div class="team">
			<input class="buttonBack" type=button value="powrot"
				onCLick="history.back()"> <br />
			<c:if test="${!empty messageTypeAction}">
				<c:if test="${messageTypeAction==1}">
					<h3>Druzyna - wydarzenia</h3>
				</c:if>
				<c:if test="${messageTypeAction==2}">
					<h3>Druzyna - zadania</h3>
				</c:if>
			</c:if>

			<c:if test="${!empty actionList}">
				<c:forEach items="${actionList}" var="action">
					<div id="element"
						onclick="window.location = '<%=request.getContextPath()%>/action/one/${action.idAction} '">
						<table>
							<tr>
								<th colspan="1" class="name">${action.name}</th>
								<th colspan="4" class="description">${action.description}</th>
							</tr>
							<tr>
								<th colspan="2" class="priority"><b>Priorytet:</b>
									${action.priority}%</th>


								<th colspan="2" class="team"><b>Druzyna:</b>
									${action.idTeam.name}</th>

							</tr>
							<tr>
								<c:if test="${action.typeAction==1}">
									<th colspan="2" class="event"><b>Typ: </b> wydarzenie,
										${action.periodic}</th>
								</c:if>

								<c:if test="${action.typeAction==2}">
									<th colspan="2" class="task"><b>Typ: </b> zadanie,
										${action.periodic}</th>
								</c:if>

								<c:if
									test="${action.dateWhen.getTime() >= objectDate.getTime()}">
									<th colspan="2" class="date"><b>Data:</b>
										${action.dateWhen.toLocaleString()}</th>
								</c:if>

								<c:if test="${action.dateWhen.getTime() < objectDate.getTime()}">
									<th colspan="2" class="datePast"><b>Data:</b>
										${action.dateWhen.toLocaleString()}</th>
								</c:if>

							</tr>
						</table>
					</div>
				</c:forEach>

			</c:if>
		</div>
	</div>
</body>
</html>