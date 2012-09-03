
<div class="diary">
	<h3>Dziennik</h3>
	<%@ page import="java.util.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<%
		int yearDisplay = Integer.parseInt(request.getAttribute("year")
				.toString());
		int monthDisplay = Integer.parseInt(request.getAttribute("month")
				.toString());

		int dayCurrent = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int monthCurrent = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);

		Calendar calendarDisplay = Calendar.getInstance();
		calendarDisplay.set(Calendar.YEAR, yearDisplay);
		calendarDisplay.set(Calendar.MONTH, monthDisplay - 1);

		//zmienne potrzebne do wyswietlenia kalendarza
		Calendar calendarTmp = calendarDisplay;
		calendarTmp.set(Calendar.DAY_OF_MONTH, 1);
		int firstDayInMonth = calendarTmp.get(Calendar.DAY_OF_WEEK);
		int blank = firstDayInMonth - 1;
		int daysInMonth = calendarDisplay
				.getActualMaximum(Calendar.DAY_OF_MONTH);
	%>

	<div id="calendar">

		<select id="selectList"
			onChange="if(this.value != 'none') document.location = this.value;">
			<option value="none">Rok</option>
			<%
				for (int i = 2005; i < 2020; i++) {
			%>
			<option
				value="<%=request.getContextPath()%>/diary/<%=i%>/<%=monthDisplay%>"><%=i%></option>
			<%
				}
			%>
		</select> <select id="selectList"
			onChange="if(this.value != 'none') document.location = this.value;">
			<option value="none">Miesiac</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/1">styczen</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/2">luty</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/3">marzec</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/4">kwiecien</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/5">maj</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/6">czerwiec</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/7">lipiec</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/8">sierpien</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/9">wrzesien</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/10">pazdziernik</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/11">listopad</option>
			<option
				value="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/12">grudzien</option>
		</select>

		<div id='selectList'>
			<a href="<%=request.getContextPath()%>/diary"> aktualna data </a>
		</div>

		<br /> <br />

		<table border=1>
			<tr>
				<th colspan=1>
					<div id='navigation'>
						<a
							href="<%=request.getContextPath()%>/diary/<%out.print(getPreviousYear(yearDisplay));%>/<%=monthDisplay%>">
							<%
								out.print("<");
							%>
						</a>
					</div>
				</th>
				<th colspan=1>

					<div id='navigation'>
						<a
							href="<%=request.getContextPath()%>/diary/<%out.print(getNextYear(yearDisplay));%>/<%=monthDisplay%>">
							<%
								out.print(">");
							%>
						</a>
					</div>
				</th>
				<th colspan=2><%=yearDisplay%></th>

				<th colspan=1>
					<%
						out.print(monthDisplay);
					%>
				</th>
				<th colspan=1>
					<div id='navigation'>
						<a
							href="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/<%out.print(getPreviousMonth(monthDisplay));%>">
							<%
								out.print("<");
							%>
						</a>
					</div>

				</th>
				<th colspan=1>
					<div id='navigation'>
						<a
							href="<%=request.getContextPath()%>/diary/<%=yearDisplay%>/<%out.print(getNextMonth(monthDisplay));%>">
							<%
								out.print(">");
							%>
						</a>
					</div>
				</th>
			</tr>
			<tr>
				<td width=42><span class='sunday'> ND </span></td>
				<td width=42>PN</td>
				<td width=42>WT</td>
				<td width=42>SR</td>
				<td width=42>CZ</td>
				<td width=42>PT</td>
				<td width=42><span class='saturday'> S </span></td>
			</tr>


			<%
				int day_count = 1;//This counts the days in the week, up to 7
				int day_num = 1; //numer dnia w miesiacu

				out.println("<tr>");
				//first we take care of those blank days
				while (blank > 0) {
					out.println("<td> - </td>");
					blank = blank - 1;
					day_count++;
				}

				//sets the first day of the month to 1 
				day_num = 1;

				//count up the days, untill we've done all of them in the month
				while (day_num <= daysInMonth) {
					String uri = request.getContextPath() + "/diary/" + yearDisplay
							+ "/" + monthDisplay + "/" + day_num;
					out.print("<td> <a href=\"" + uri + "\">");

					if (dayCurrent == day_num && monthCurrent == monthDisplay
							&& yearCurrent == yearDisplay)
						out.print("<span class='today'>");

					pageContext.setAttribute("day_num", day_num);
			%>

			<c:forEach items="${diaryList}" var="day">
				<c:if test="${day.day==day_num }">
					<%
						out.print("<div id='diaryNote'>");
					%>
				</c:if>
			</c:forEach>


			<c:out value="${day_num}" />

			<c:forEach items="${diaryList}" var="day">
				<c:if test="${day.day==day_num }">
					<%
						out.print("</div>");
					%>
				</c:if>
			</c:forEach>

			<%
				if (dayCurrent == day_num && monthCurrent == monthDisplay
							&& yearCurrent == yearDisplay)
						out.print("</span");
					out.print("</a> </td>");

					day_num++;
					day_count++;

					//Make sure we start a new row every week
					if (day_count > 7) {
						out.print("</tr><tr>");
						day_count = 1;
					}
				}

				//Finaly we finish out the table with some blank details if needed
				while (day_count > 1 && day_count <= 7) {
					out.print("<td> - </td>");
					day_count++;
				}
				out.print("</tr>");
			%>
		</table>
	</div>

</div>

<%!int getPreviousMonth(int month) {
		if (month > 1)
			return --month;
		else
			return 12;
	}

	int getNextMonth(int month) {
		if (month < 12)
			return ++month;
		else
			return 1;
	}

	int getPreviousYear(int year) {
		return --year;
	}

	int getNextYear(int year) {
		return ++year;
	}

	String getMonthName(int numberOfMonth) {
		switch (numberOfMonth) {
		case 1:
			return "Styczen";
		case 2:
			return "Luty";
		case 3:
			return "Marzec";
		case 4:
			return "Kwiecien";
		case 5:
			return "Maj";
		case 6:
			return "Czerwiec";
		case 7:
			return "Lipiec";
		case 8:
			return "Sierpien";
		case 9:
			return "Wrzesien";
		case 10:
			return "Padziernik";
		case 11:
			return "Listopad";
		case 12:
			return "Grudzien";
		default:
			return "error";
		}
	}%>
