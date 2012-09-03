<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Druzyna - uczestnicy</title>
</head>
<body>
	<div class="team">
		<input class="buttonBack" type=button value="powrot"
			onCLick="history.back()"> <br />

		<h3>Druzyna - uczestnicy</h3>

		<c:if test="${!empty listUserTeam}">
			<table>
				<c:forEach items="${listUserTeam}" var="userTeam">
					<tr>
						<td>
							<div id="element"
								onclick="window.location = '<%=request.getContextPath()%>/team/oneUser/${userTeam.idUserTeam}'">
								<div id="element">
									${userTeam.idUser.login}&nbsp;&nbsp;
									<c:if test="${!empty isAdmin}">
										<c:if test="${isAdmin==true}">
											<c:choose>
												<c:when test="${userTeam.isAdmin==true}">
													<b>(Admin)</b>
												</c:when>
												<c:otherwise>
				(user) 			<td><input class="buttonAction" type=button
														value="Ustaw jako admina"
														onCLick="window.location = '<%= request.getContextPath() %>/team/one/${userTeam.idTeam.idTeam}/user/${userTeam.idUser.idUser}/privilege'">
													</td>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:if>

								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>