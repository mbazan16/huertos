<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="context" value="${pageContext.request.contextPath}" />
<fmt:setBundle basename="app"/>
<head>
<meta charset="ISO-8859-1">
<title><fmt:message key="title.error"/></title>
<link href="${context}/css/estilo.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>			
		<nav>
			<ul>
				<li><a href="${context}/" class="active">Home</a></li>
				<li><a href="${context}/huertos">Huertos</a></li>
				<li><a href="${context}/macetas">Macetas</a></li>
				<li><a href="${context}/plantas">Plantas</a></li>
			</ul>
		</nav>
	</header>
	<main>
	<section id="stitulo">
		<h1><fmt:message key="titulo.error"/></h1>
	
	</section>
	
	 <section id="serror">
	 	<fmt:bundle basename="errors">
	   		<fmt:message key="${error}"/>
	   	</fmt:bundle>
	 </section>
	</main>
	<footer> </footer>
</body>
</html>