<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="context" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="app"/> 
<head>
<meta charset="ISO-8859-1">
<title><fmt:message key="title.plantas"/></title>
<link href="${context}/css/estilo.css" rel="stylesheet" type="text/css">
</head>
<body>

	<header>
		<nav>
			<ul>
				<li><a href="${context}/"><fmt:message key="navegacion.home"/></a></li>
				<li><a href="${context}/huertos"><fmt:message key="navegacion.huertos"/></a></li>
				<li><a href="${context}/macetas"><fmt:message key="navegacion.macetas"/></a></li>
				<li><a href="${context}/plantas" class="active"><fmt:message key="navegacion.plantas"/></a></li>
			    <c:if test="${locale=='es'}">
				<li>
				   <a href="${context}/idioma?locale=en">
				   <img src="https://img.icons8.com/emoji/48/000000/united-kingdom-emoji.png" width=20 height=20/>
				   </a>
				 </li> 
				</c:if>
				<c:if test="${locale=='en'}">
				
				<li>
				   <a href="${context}/idioma?locale=es">
				     <img src="https://img.icons8.com/emoji/48/000000/spain-emoji.png" width=20 height=20/>
				   </a>
				 </li>
				</c:if>
			</ul>
		</nav>
	</header>
	<main>
	<section id="stitulo">
		<h1><fmt:message key="titulo.plantas"/></h1>
	</section>
	<c:if test="${not empty plantas}">
	<section id="slistado">
		<table id="tlistado">
			<tr>
				<th><fmt:message key="huerto"/></th>
				<th><fmt:message key="maceta"/></th>
				<th><fmt:message key="planta.nombre"/></th>
				<th></th>
			</tr>
			<c:forEach items="${plantas}" var="planta">
				<tr>
					<td><a href="${context}/huerto/v?id=${planta.maceta.huerto.id}">${planta.maceta.huerto.nombre}</a></td>
					<td><a href="${context}/maceta/v?id=${planta.maceta.id}">${planta.maceta.codigo}</a></td>
					<td>${planta.nombre}</td>
					<td>
					<a href="${context}/planta/r?id=${planta.id}"><img src="https://img.icons8.com/color/48/000000/waste--v1.png" width=15 height=15/></a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	</c:if>
	<c:if test="${empty plantas}">
	 <section id="stext">
	   <fmt:message key="text.noPlantas"/>
	 </section>
	</c:if>
	<section id="snuevo" class="modal">
		<h1><fmt:message key="planta.nueva"/></h1>
		<form id="fnuevo" method="post" action="${context}/planta/n">
			<label for="idMaceta"><fmt:message key="maceta"/></label> 
			<select id="idMaceta"	name="idMaceta">
			    <option value=""><fmt:message key="form.seleccionar"/></option>
			    <option value=""><fmt:message key="maceta.no"/></option>
			   <c:forEach items="${macetas}" var="maceta">
				<option value="${maceta.id}">${maceta.huerto.nombre}-${maceta.codigo}</option>
			</c:forEach>
			</select>
			<label for="codigo"><fmt:message key="planta.nombre"/></label> 
			<input type="text" id="nombre"
				name="nombre" title="<fmt:message key="planta.nombre"/>" placeholder="<fmt:message key="planta.nombre"/>" required />
			<input type="submit" value="<fmt:message key="form.enviar"/>">
		</form>
	</section>
		<c:if test="${not empty message}">
	 <section id="smessage">
	   <fmt:bundle basename="messages">
	      	<fmt:message key="${message}">
	   		<c:if test="${not empty parametro}">
	   		${parametro}
	   		<fmt:param value="${parametro}"/>
	   		</c:if>
	   		</fmt:message>
	   </fmt:bundle>
	 </section>
	 </c:if>
	 
	 <c:if test="${not empty error}">
	 <section id="serror">
	 	<fmt:bundle basename="errors">
	   		<fmt:message key="${error}"/>
	   	</fmt:bundle>
	 </section>
	 </c:if>
	</main>
	<footer> </footer>
</body>
</html>