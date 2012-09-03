<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="menuLeft">
	<ul>
		<li>USLUGI ZEWNETRZNE</li>
		<li><a class="buttonCalculator"
			href="<%=request.getContextPath()%>/calculator">KALKULATOR</a></li>
		<li><a class="buttonExchange"
			href="<%=request.getContextPath()%>/currency">KURSY WALUT</a></li>
		<li><a class="buttonWeather"
			href="<%=request.getContextPath()%>/weather">POGODA</a></li>
		<li>KONTO</li>
		<li><a href="<%=request.getContextPath()%>/account">MOJE
				KONTO</a></li>
		<li><a href="<%=request.getContextPath()%>/about">PRACA MGR</a></li>
		<li><a href="<%=request.getContextPath()%>/admin">ADMIN</a></li>
		<li><a href="<c:url value='/j_spring_security_logout' />">WYLOGUJ</a></li>
	</ul>
</div>