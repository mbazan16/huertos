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
<title><fmt:message key="title.maceta"/> ${maceta.codigo}</title>
<link href="${context}/css/estilo.css" rel="stylesheet" type="text/css">
<script>
	function verModificar() {
		document.getElementById("smodificar").style.display = "block";
	}
	function modificar() {
		if (confirm("¿Desea cambiar los datos de la maceta?")) {
			document.getElementById("smodificar").style.display = "none";
			document.getElementById("fmodificar").submit();
		}
	}
	function eliminar() {
		if (confirm("¿Desea eliminar la maceta?")) {
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
		<h1><fmt:message key="titulo.maceta"/> ${maceta.codigo}</h1>
	</section>
	<section id="sdatos">
		<table id="datos">
		 <tr>
				<th><fmt:message key="huerto"/></th>
				<td>${maceta.huerto.nombre}</td>
				<td></td>
			</tr>
			<tr>
				<th><fmt:message key="maceta.codigo"/></th>
				<td>${maceta.codigo}</td>
				<td><a href="javascript:verModificar()"><img src="https://img.icons8.com/color/48/000000/edit--v1.png" width=15 height=15/></a> <a
					href="javascript:eliminar()"><img src="https://img.icons8.com/color/48/000000/waste--v1.png" width=15 height=15/></a></td>
			</tr>
			<tr>
				<th><fmt:message key="maceta.tamanio"/></th>
				<td>${maceta.tamanio}-${maceta.tamanio.cms}</td>
				<td></td>
			</tr>
			<tr>
				<th><fmt:message key="maceta.material"/></th>
				<td>${maceta.material}</td>
				<td></td>
			</tr>
		</table>
	</section>
	<section id="smodificar" style="display: none;">
		<h1><fmt:message key="maceta.modificar"/></h1>
		<form id="fmodificar" method="post" action="${context}/maceta/m">
			<input hidden="true" type="text" name="idHuerto" value="${maceta.huerto.id}" />
			<input hidden="true" type="text" name="id" value="${maceta.id}" />
			<label for="codigo"><fmt:message key="maceta.codigo"/></label> 
			<input type="text" id="codigo"
				name="codigo" title="<fmt:message key="maceta.codigo"/>" value="${maceta.codigo}" readonly />
			<label for="tamanio"><fmt:message key="maceta.tamanio"/></label> 
			<select id="tamanio"	name="tamanio" required>
			   <c:forEach items="${tamanios}" var="tamanio">
				<option value="${tamanio}" ${maceta.tamanio == tamanio ? 'selected' : ''}>${tamanio}-${tamanio.cms}cms</option>
			</c:forEach>
			</select>
			<label for="material"><fmt:message key="maceta.material"/></label> 
			<select id="material" name="material" required>
			   <c:forEach items="${materiales}" var="material">
				<option value="${material}" ${maceta.material == material ? 'selected' : ''} >${material}</option>
			</c:forEach>
			</select>
			<input type="button" value="<fmt:message key="form.enviar"/>"	onClick="javascript:modificar()">
		</form>
	</section>
	<section id="seliminar" style="display: none;">
		<form id="feliminar" method="post" action="${context}/maceta/r">
			<input hidden="true" type="text" name="id" value="${maceta.id}" />
		</form>
	</section>
	<c:if test="${not empty plantas}">
	<section id="slistado">
		<table id="tlistado">
		<caption><fmt:message key="listado.plantas"/></caption>
		  	<tr>
				<th><fmt:message key="planta.nombre"/></th>
				<th></th>
			</tr>
			<c:forEach items="${plantas}" var="planta">
				<tr>
					<td>${planta.nombre}</td>
					<td><a href="${context}/planta/r?id=${planta.id}&forward=/maceta/v?id=${planta.maceta.id}"><img src="https://img.icons8.com/color/48/000000/waste--v1.png" width=15 height=15/></a></td>
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
			<input hidden="true" type="text" name="forward" value="/maceta/v" />
			<input hidden="true" type="text" name="idMaceta" value="${maceta.id}" />
			<label for="nombre"><fmt:message key="planta.nombre"/></label> 
			<input type="text" id="nombre"
				name="nombre" title="<fmt:message key="planta.nombre"/>" placeholder="<fmt:message key="planta.nombre"/>" required />
			<input type="submit" value="<fmt:message key="form.enviar"/>">
		</form>
	</section>
	</main>	<c:if test="${not empty message}">
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
	<footer> </footer>
</body>
</html>