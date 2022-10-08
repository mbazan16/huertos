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
<title><fmt:message key="title.huerto"/> ${huerto.nombre}</title>
<link href="${context}/css/estilo.css" rel="stylesheet" type="text/css">
<script>
	function verModificar() {
		document.getElementById("smodificar").style.display = "block";
	}
	function modificar() {
		if (confirm("¿Desea cambiar el nombre del huerto?")) {
			document.getElementById("smodificar").style.display = "none";
			document.getElementById("fmodificar").submit();
		}
	}
	function eliminar() {
		if (confirm("¿Desea eliminar el huerto?")) {
			document.getElementById("feliminar").submit();
		}
	}
</script>
</head>
<body>
	
	<header>
		<nav>
			<ul>
				<li><a href="${context}/"><fmt:message key="navegacion.home"/></a></li>
				<li><a href="${context}/huerto" class="active"><fmt:message key="navegacion.huertos"/></a></li>
				<li><a href="${context}/maceta"><fmt:message key="navegacion.macetas"/></a></li>
				<li><a href="${context}/planta"><fmt:message key="navegacion.plantas"/></a></li>
			   <c:if test="${locale=='es'}">
				<li>
				   <a href="${context}/idioma/en">
				   <img src="https://img.icons8.com/emoji/48/000000/united-kingdom-emoji.png" width=20 height=20/>
				   </a>
				 </li> 
				</c:if>
				<c:if test="${locale=='en'}">
				
				<li>
				   <a href="${context}/idioma/es">
				     <img src="https://img.icons8.com/emoji/48/000000/spain-emoji.png" width=20 height=20/>
				   </a>
				 </li>
				</c:if>
			</ul>
		</nav>
	</header>
	<main>
	<section id="stitulo">
		<h1><fmt:message key="titulo.huerto"/>  ${huerto.nombre}</h1>
	</section>
	<section id="sdatos">
		<table id="datos">
			<tr>
				<th><fmt:message key="huerto.nombre"/></th>
				<td>${huerto.nombre}</td>
				<td><a href="javascript:verModificar()"><img src="https://img.icons8.com/color/48/000000/edit--v1.png" width=15 height=15/></a></td>
			</tr>
		</table>
	</section>
	<section id="smodificar" style="display: none;">
		<h1><fmt:message key="huerto.nombre.cambiar"/></h1>
		<form id="fmodificar" method="post" action="${context}/huerto/${huerto.id}">
			<input hidden="true" type="text" name="id" value="${huerto.id}" /> 			
			<label for="nombre"><fmt:message key="huerto.nombre"/></label> 
			<input type="text" id="nombre" name="nombre" title="<fmt:message key="huerto.nombre"/>" required	value="${huerto.nombre}" /> 
			<input type="button" value="<fmt:message key="form.enviar"/>"	onClick="javascript:modificar()">
		</form>
	</section>
	<section id="seliminar" style="display: none;">
		<form id="feliminar" method="post" action="${context}/huerto/r/${huerto.id}">			
		</form>
	</section>
	<c:if test="${not empty macetas}">
	<section id="slistado">
		<table id="tlistado">
		<caption><fmt:message key="listado.macetas"/></caption>
		  	<tr>
				<th><fmt:message key="maceta.codigo"/></th>
				<th><fmt:message key="maceta.tamanio"/></th>
				<th><fmt:message key="maceta.material"/></th>
				<th></th>
			</tr>
			<c:forEach items="${macetas}" var="maceta">
				<tr>
					<td>${maceta.codigo}</td>
					<td>${maceta.tamanio}</td>
					<td>${maceta.material}</td>
					<td><a href="${context}/maceta/${maceta.id}"><img src="https://img.icons8.com/color/search" width=15 height=15></a></td>
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
	 	 
	<section id="snuevo" class="modal">
		<h1> <fmt:message key="maceta.nueva"/></h1>
		<form id="fnuevo" method="post" action="${context}/maceta">
			<input hidden="true" type="text" name="forward" value="/huerto/${huerto.id}" />
			<input hidden="true" type="text" name="huerto.id" value="${huerto.id}" />
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