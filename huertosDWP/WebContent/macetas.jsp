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
<title><fmt:message key="title.macetas"/></title>
<link href="${context}/css/estilo.css" rel="stylesheet" type="text/css">
</head>
<body>

	<header>
		<nav>
			<ul>
				<li><a href="${context}/"><fmt:message key="navegacion.home"/></a></li>
				<li><a href="${context}/huertos"><fmt:message key="navegacion.huertos"/></a></li>
				<li><a href="${context}/macetas" class="active"><fmt:message key="navegacion.macetas"/></a></li>
				<li><a href="${context}/plantas"><fmt:message key="navegacion.plantas"/></a></li>
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
		<h1><fmt:message key="titulo.macetas"/></h1>
	</section>
	<c:if test="${not empty macetas}">
	<section id="slistado">
		<table id="tlistado">
			<tr>
				<th><fmt:message key="maceta.codigo"/></th>
				<th><fmt:message key="maceta.tamanio"/></th>
				<th><fmt:message key="maceta.material"/></th>
				<th><fmt:message key="huerto.nombre"/></th>
				<th><fmt:message key="planta.numero"/></th>
				<th></th>
			</tr>
			<c:forEach items="${macetas}" var="maceta">
				<tr>
					<td>${maceta.codigo}</td>
					<td>${maceta.tamanio}-${maceta.tamanio.cms}</td>
					<td>${maceta.material}</td>
					<td>${maceta.nombreHuerto}</td>
					<td>${maceta.nPlantas}</td>
					<td><a href="${context}/maceta/v?id=${maceta.id}"><img src="https://img.icons8.com/color/search" width=15 height=15></a>
					<a href="${context}/maceta/r?id=${maceta.id}"><img src="https://img.icons8.com/color/48/000000/waste--v1.png" width=15 height=15/></a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	</c:if>
	<c:if test="${empty macetas}">
	 <section id="stext">
	   <fmt:message key="text.noMacetas"/>
	 </section>
	</c:if>
	<c:if test="${not empty huertos}">
	<section id="snuevo" class="modal">
		<h1><fmt:message key="maceta.nueva"/></h1>
		<form id="fnuevo" method="post" action="${context}/maceta/n">
			<label for="idHuerto"><fmt:message key="huerto"/></label> 
			<select id="idHuerto"	name="idHuerto" required>
			    <option><fmt:message key="form.seleccionar"/></option>
			   <c:forEach items="${huertos}" var="huerto">
				<option value="${huerto.key}">${huerto.value}</option>
			</c:forEach>
			</select>
			<label for="codigo"><fmt:message key="maceta.codigo"/></label> 
			<input type="text" id="codigo"
				name="codigo" title="<fmt:message key="maceta.codigo"/>" placeholder="02578932" required />
			<label for="tamanio"><fmt:message key="maceta.tamanio"/></label> 
			<select id="tamanio"	name="tamanio" required>
			   <option><fmt:message key="form.seleccionar"/></option>
			   <c:forEach items="${tamanios}" var="tamanio">
				<option value="${tamanio}">${tamanio}-${tamanio.cms}cms</option>
			</c:forEach>
			</select>
			<label for="material"><fmt:message key="maceta.material"/></label> 
			<select id="material" name="material" required>			    
			    <option><fmt:message key="form.seleccionar"/></option>
			   <c:forEach items="${materiales}" var="material">
				<option value="${material}">${material}</option>
			</c:forEach>
			</select>
			<input type="submit" value="<fmt:message key="form.enviar"/>">
		</form>
	</section>
	</c:if>
	<c:if test="${empty huertos}">
	 <section id="stext">
	   <fmt:message key="text.noHuertos"/>
	 </section>
	</c:if>
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