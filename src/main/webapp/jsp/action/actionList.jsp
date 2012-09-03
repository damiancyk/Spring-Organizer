<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" import="java.util.*"%>
<%@page contentType="text/html" import="net.organizer.form.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Wydarzenia</title>
<meta name="keywords" content="sitemesh" />
</head>
<body>
	<div class="action">
		<c:if test="${!empty fromDiary}">
			<input class="buttonBack" type=button value="powrot"
				onCLick="history.back()">
			<br />
		</c:if>
		<div class="${messageType}">
			<h3>${messageHeader}</h3>
			<div class="messageGood">...Aby dodac wpis, przejdz do menu
				odpowiedniej druzyny!</div>

			<c:if test="${empty fromDiary}">
				<div class="kindOfElement">
					Wyswietlane pozycje:
					<c:if test="${!empty messageTypeEvent}">
						<span> ${messageTypeEvent}</span>
					</c:if>

					<br /> <select class="kind"
						onChange="if(this.value != 'none') document.location = this.value;">
						<option value="none">...wybierz zakres...</option>
						<option value="none"></option>
						<option value="today">dzisiaj</option>
						<option value="none">---</option>
						<option value="nearestWeek">najblizszy tydzien</option>
						<option value="nearestMonth">najblizszy miesiac</option>
						<option value="nearestYear">najblizszy rok</option>
						<option value="none">---</option>
						<option value="future">wszystkie przyszle</option>
						<option value="past">wszystkie zakonczone</option>
						<option value="all">wszystkie</option>
						<option value="none"></option>
					</select>

				</div>
			</c:if>

			<c:if test="${!empty actionList}">
				<c:set var="timeNow"
					value="<%=Calendar.getInstance().getTime().getTime()%>"
					scope="page" />

				<c:forEach items="${actionList}" var="action">

					<div id="element"
						onclick="window.location = '<%=request.getContextPath()%>/action/one/${action.idAction} '">
						<c:if test="${action.dateWhen.getTime()<timeNow}">
						------------ZALEGŁE------------
						</c:if>
						<c:if test="${action.dateWhen.getTime()>=timeNow}">
						------------PRZYSZŁE------------
						</c:if>
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
									<th colspan="2" class="event"><b>Typ: </b> wydarzenie<c:if
											test="${action.periodic!='no'}">
										, okresowe
										</c:if></th>
								</c:if>

								<c:if test="${action.typeAction==2}">
									<th colspan="2" class="task"><b>Typ: </b> zadanie<c:if
											test="${action.periodic!='no'}">
										, okresowe
										</c:if></th>
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