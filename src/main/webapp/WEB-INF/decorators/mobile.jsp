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
	href="<c:url value="/resources/style/mobile.css" />" media="all" />

<dec:head />
</head>
<body>
	<div id="container">
		<div id="menuUp">
			<ul>
				<li><a href="<%=request.getContextPath()%>/menuTeam">GRUPOWE</a></li>
				<li><a href="<%=request.getContextPath()%>/menuLocal">LOKALNE</a></li>
				<li><a href="<%=request.getContextPath()%>/menuAnother">POZOSTALE</a></li>
			</ul>
		</div>
		<div id="contentsBody">
			<dec:body />
		</div>

		<div id="footer">############Designed by Damian
			Pieta############</div>
	</div>
</body>
</html>
